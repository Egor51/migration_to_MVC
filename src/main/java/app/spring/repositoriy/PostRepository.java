package app.spring.repositoriy;

import app.spring.model.Post;
import app.spring.model.PostDto;

import java.util.Collection;

public interface PostRepository {
    Collection<PostDto> all();

    Collection<PostDto> getById(long id);

    Post save(Post post);

    void removeById(long id);

    Collection<Post> allForAdmin();
}