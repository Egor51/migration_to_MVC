package app.spring.repositoriy;
import app.spring.exception.NotFoundException;
import app.spring.model.Post;
import app.spring.model.PostDto;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final ConcurrentHashMap<Long, Post> data = new ConcurrentHashMap<>();
    private final AtomicLong COUNT = new AtomicLong(0);

    @Override
    public Collection<PostDto> all() {
        if (!data.isEmpty()) {
            return new ArrayList<>(data.values()).stream()
                    .filter(Post::getRemove)
                    .map(this::toPostDto)
                    .collect(Collectors.toList());
        } else {
            throw new NotFoundException("List's posts empty");
        }
    }

    @Override
    public Collection<PostDto> getById(long id) {
        if (data.get(id).getRemove()) {
            return new ArrayList<>(data.values()).stream()
                    .filter(x -> x.getId() == id)
                    .filter(Post::getRemove)
                    .map(this::toPostDto)
                    .collect(Collectors.toList());
        } else {
            throw new NotFoundException("This is Post remove");
        }
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(COUNT.incrementAndGet());
            data.put(post.getId(), post);
        } else {
            if (data.containsKey(post.getId()))
                data.replace(post.getId(), post);
            else
                throw new NotFoundException("Failed to update post");
        }
        return post;
    }

    @Override
    public void removeById(long id) {
        data.get(id).setRemove(true);
    }

    @Override
    public Collection<Post> allForAdmin() {
        if (!data.isEmpty()) {
            return new ArrayList<>(data.values());
        } else {
            throw new NotFoundException("List's posts empty");
        }
    }

    public PostDto toPostDto(Post post) {
        return new PostDto(post.getId(), post.getContent());
    }
}
