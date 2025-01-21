package org.example.controller;

import org.example.model.PostDto;
import org.example.service.PostService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<PostDto> all(HttpServletResponse response) {
        return service.all();

    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    public PostDto save(@RequestBody PostDto post) {
        return service.save(post);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }
}
