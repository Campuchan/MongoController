package eu.campuchan.MongoController;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Usuario findByNombre(String autorNombre);
}
