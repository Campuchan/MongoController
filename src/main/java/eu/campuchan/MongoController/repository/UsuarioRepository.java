package eu.campuchan.MongoController.repository;

import eu.campuchan.MongoController.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Usuario findByNombre(String autorNombre);

    boolean existsByCorreo(String correo);
}
