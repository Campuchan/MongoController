package eu.campuchan.MongoController.controller;

import eu.campuchan.MongoController.dto.PostRequest;
import eu.campuchan.MongoController.model.Post;
import eu.campuchan.MongoController.service.PostService;
import eu.campuchan.MongoController.model.Usuario;
import eu.campuchan.MongoController.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public Post createPost(@RequestBody @Validated PostRequest postRequest) {
        Post post = postRequest.getPost();
        String password = postRequest.getPassword();

        if (post.getAutorNombre() != null) {
            Usuario autor = usuarioService.findByNombre(post.getAutorNombre());
            if (autor != null) {
                if (password != null) {
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    if (encoder.matches(password, autor.getPassword())) {
                        post.setAutor(autor);
                    } else {
                        throw new RuntimeException("Password not sent or wrong 2");
                    }
                } else {
                    throw new RuntimeException("Password not sent or wrong");
                }
            } else {
                throw new RuntimeException("Author not found");
            }
        }

        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post postDetails) {
        return postService.updatePost(id, postDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id){
        postService.deletePost(id);
    }
}
