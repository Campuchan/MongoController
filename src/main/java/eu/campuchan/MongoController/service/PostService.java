package eu.campuchan.MongoController.service;

import eu.campuchan.MongoController.model.Post;
import eu.campuchan.MongoController.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(String id){
        return postRepository.findById(id).orElse(null);
    }

    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public Post updatePost(String id, Post postDetails) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setTitulo(postDetails.getTitulo());
            post.setContenido(postDetails.getContenido());
            return postRepository.save(post);
        }
        return null;
    }

    public void deletePost(String id){
        postRepository.deleteById(id);
    }
}
