package org.example.repository;


import org.example.model.Post;
import org.example.model.PostDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    private List<PostDto> listPost = new CopyOnWriteArrayList<>();
    private long numberId;

    public List<Post> all() {
        return listPost.stream().filter(o -> !o.isRemoved()).map(PostDto::getPost).collect(Collectors.toList());
    }

    public Optional<Post> getById(long id) {
        Optional<Post> postById = Optional.empty();
        if (id > numberId) {
            return postById;
        } else {
            for (PostDto post : listPost) {
                if (post.getPost().getId() == id && !post.isRemoved()) {
                    postById = Optional.of(post.getPost());
                    break;
                }
            }
        }
        return postById;
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(++numberId);

            listPost.add(new PostDto(post, false));
            return post;
        } else {
            for (int i = 0; i < listPost.size(); i++) {
                if (listPost.get(i).getPost().getId() == post.getId() && !listPost.get(i).isRemoved()) {
                    listPost.get(i).getPost().setContent(post.getContent());
                    return post;
                }
            }
        }
        return null;
    }

    public void removeById(long id) {
        if (id > numberId) {
            return;
        }
        for (int i = 0; i < listPost.size(); i++) {
            if (listPost.get(i).getPost().getId() == id) {
                listPost.get(i).setRemoved(true);
            }
        }
    }
}
