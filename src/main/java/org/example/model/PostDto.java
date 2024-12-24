package org.example.model;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PostDto {
    private Post post;
    private boolean removed;

    public PostDto(Post post, boolean removed) {
        this.post = post;
        this.removed = removed;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
