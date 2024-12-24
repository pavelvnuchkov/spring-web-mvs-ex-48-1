package org.example.repository;


import org.example.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class PostRepository {
    private List<Post> listPost = new CopyOnWriteArrayList<>();
    private long numberId;

    public List<Post> all() {
        return listPost;
    }

    public Optional<Post> getById(long id) {
        Optional<Post> postById = Optional.empty();
        if (id > numberId) {
            return postById;
        } else {
            for (Post post : listPost) {
                if (post.getId() == id) {
                    postById = Optional.of(post);
                    break;
                }
            }
        }
        return postById;
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(++numberId);
            listPost.add(post);
            return post;
        } else {
            for (int i = 0; i < listPost.size(); i++) {
                if (listPost.get(i).getId() == post.getId()) {
                    listPost.get(i).setContent(post.getContent());
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
            if (listPost.get(i).getId() == id) {
                listPost.remove(i);
            }
        }
    }
}
