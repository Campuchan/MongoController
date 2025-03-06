package eu.campuchan.MongoController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario createUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }


    public Usuario findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }
}

