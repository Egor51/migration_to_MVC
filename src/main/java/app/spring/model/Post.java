package app.spring.model;

public class Post {
    private long id;
    private String content;

    private boolean remove = false;

    public Post() {
    }

    public Post(long id, String content,boolean remove) {
        this.id = id;
        this.content = content;
        this.remove = remove;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }
    public boolean getRemove(){return remove;}
}
