package eu.campuchan.MongoController.controller;

import eu.campuchan.MongoController.dto.PostDTO;
import eu.campuchan.MongoController.dto.PostRequest;
import eu.campuchan.MongoController.model.Comentario;
import eu.campuchan.MongoController.model.Post;
import eu.campuchan.MongoController.model.Usuario;
import eu.campuchan.MongoController.service.ComentarioService;
import eu.campuchan.MongoController.service.PostMapper;
import eu.campuchan.MongoController.service.PostService;
import eu.campuchan.MongoController.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {
    private static final Logger logger = LoggerFactory.getLogger(ComentarioController.class);

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ComentarioMapper comentarioMapper;


    @GetMapping()
    public ResponseEntity<?> getAllPosts(){
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
        Comentario comentario = comentarioService.getComentarioById(id);
        if (comentario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comentarioMapper.toDto(post));
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody @Validated PostRequest postRequest) {
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
                        return ResponseEntity.badRequest().body("Password not sent or wrong");
                    }
                } else {
                    return ResponseEntity.badRequest().body("Password not sent or wrong");
                }
            } else {
                return ResponseEntity.badRequest().body("Account not found");
            }
        }

        return  ResponseEntity.ok(postMapper.toDto(postService.createPost(post)));
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
