package eu.campuchan.MongoController.service;


import eu.campuchan.MongoController.exception.DuplicateEmailException;
import eu.campuchan.MongoController.model.Usuario;
import eu.campuchan.MongoController.repository.UsuarioRepository;
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
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new DuplicateEmailException("Email already exists: " + usuario.getCorreo());
        }

        return usuarioRepository.save(usuario);
    }


    public Usuario findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    public Usuario updateUsuario(String id, Usuario usuario) {

        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
               //se hace exception directamente para que no haga los metodos de abajo si eso
        existingUsuario.setNombre(usuario.getNombre());
        existingUsuario.setCorreo(usuario.getCorreo());
        existingUsuario.setFotoUrl(usuario.getFotoUrl());
        return usuarioRepository.save(existingUsuario);
    }
}

