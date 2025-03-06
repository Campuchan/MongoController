package eu.campuchan.MongoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public List<Usuario> getAllUsuarios() { return usuarioService.getAllUsuarios(); }

    @PostMapping()
    public Usuario createUsuario(@RequestBody @Validated Usuario usuario){
        return usuarioService.createUsuario(usuario);
    }
}
