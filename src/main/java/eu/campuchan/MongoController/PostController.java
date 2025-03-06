package eu.campuchan.MongoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public Post createPost(@RequestBody @Validated Post post) {
        if (post.getAutorNombre() != null) {
            Usuario autor = usuarioService.findByNombre(post.getAutorNombre());
            if (autor != null) {
                post.setAutor(autor);
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
