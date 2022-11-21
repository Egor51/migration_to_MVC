package app.spring.model;

public class PostDto {
   private  long id;
   private String Post;

    public PostDto(long id, String post) {
        this.id = id;
        this.Post = post;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String post) {
        Post = post;
    }
}
