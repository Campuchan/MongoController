package eu.campuchan.MongoController;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public class UsuarioRepository extends MongoRepository<Usuario, String> {

}
