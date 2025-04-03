package eu.campuchan.MongoController.repository;

import eu.campuchan.MongoController.model.Comentario;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepository {
    Optional<Comentario> findById(Long Id);
}
