package org.example.model;

public class Post {
    private PostDto post;
    private boolean removed;

    public Post(PostDto post, boolean removed) {
        this.post = post;
        this.removed = removed;
    }

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
