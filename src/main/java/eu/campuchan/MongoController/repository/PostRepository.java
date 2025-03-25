package eu.campuchan.MongoController.repository;

import eu.campuchan.MongoController.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    Optional<Post> findByPostId(Long postId);
}
