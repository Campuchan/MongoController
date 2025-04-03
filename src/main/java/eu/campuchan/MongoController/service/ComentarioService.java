package eu.campuchan.MongoController.service;

import eu.campuchan.MongoController.model.Comentario;
import eu.campuchan.MongoController.model.Post;
import eu.campuchan.MongoController.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario getComentarioById(Long id) {
        return comentarioRepository.findById(id).orElse(null);
    }
}
