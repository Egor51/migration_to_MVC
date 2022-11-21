package app.spring.controller;

import app.spring.model.Post;
import app.spring.model.PostDto;
import app.spring.service.PostService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<PostDto> all(){
        return service.all();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    public Post save(@RequestBody Post post) {
        return service.save(post);
    }


    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }

    @GetMapping("/admin")
    public Collection<Post> allForAdmin(){
        return service.allForAdmin();
    }

}
