package app.spring.service;


import app.spring.model.Post;
import app.spring.model.PostDto;
import app.spring.repositoriy.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PostService {
    // сервис завязан на интерфейс, а не на конкретную реализацию
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Collection<PostDto> all() {
        return repository.all();
    }

    public Collection<PostDto> getById(long id) {
        return repository.getById(id);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }

    public Collection<Post> allForAdmin() {
        return repository.allForAdmin();
    }
}

