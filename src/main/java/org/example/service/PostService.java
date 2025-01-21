package org.example.service;


import org.example.exception.NotFoundException;
import org.example.model.PostDto;
import org.example.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<PostDto> all() {
        return repository.all();
    }

    public PostDto getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public PostDto save(PostDto post) {
        PostDto responsePost = repository.save(post);
        if (responsePost == null) {
            throw new NotFoundException();
        }
        return responsePost;
    }

    public void removeById(long id) {
        repository.removeById(id);

    }
}

