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



    public Collection<PostDto> all() {
        List <Post> postForUser = new ArrayList<>(data.values());
        return postForUser.stream()
                .filter(x-> !x.getRemove())
                .map(this::toPostDto)
                .collect(Collectors.toList());

    }

    public Optional<Post> getById(long id) {
        if (!data.get(id).getRemove()) {
            return Optional.ofNullable(data.get(id));
        } else throw new NotFoundException("This is Post remove");
    }


    public Post save(Post post) {
            if (post.getId() == 0 ) {
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

    public void removeById(long id) {
        data.get(id).setRemove(true);
    }

    @Override
    public Collection<Post> allForAdmin() {
        return new ArrayList<>(data.values());
    }

    public PostDto toPostDto(Post post) {
        return new PostDto(post.getId(), post.getContent());
    }
}

