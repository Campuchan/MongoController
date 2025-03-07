package eu.campuchan.MongoController.dto;

import eu.campuchan.MongoController.model.Post;

public class PostRequest {
    private Post post;
    private String password;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PostRequest{" +
                "post=" + post.toString() +
                ", password='" + password + '\'' +
                '}';
    }
}