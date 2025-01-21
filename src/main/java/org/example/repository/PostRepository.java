package org.example.repository;


import org.example.model.PostDto;
import org.example.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    private List<Post> listPost = new CopyOnWriteArrayList<>();
    private long numberId;

    public List<PostDto> all() {
        return listPost.stream().filter(o -> !o.isRemoved()).map(Post::getPost).collect(Collectors.toList());
    }

    public Optional<PostDto> getById(long id) {
        Optional<PostDto> postById = Optional.empty();
        if (id > numberId) {
            return postById;
        } else {
            for (Post post : listPost) {
                if (post.getPost().getId() == id && !post.isRemoved()) {
                    postById = Optional.of(post.getPost());
                    break;
                }
            }
        }
        return postById;
    }

    public PostDto save(PostDto post) {
        if (post.getId() == 0) {
            post.setId(++numberId);

            listPost.add(new Post(post, false));
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
