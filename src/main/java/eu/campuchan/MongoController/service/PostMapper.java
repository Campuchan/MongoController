package eu.campuchan.MongoController.service;

import eu.campuchan.MongoController.dto.PostDTO;
import eu.campuchan.MongoController.dto.UsuarioDTO;
import eu.campuchan.MongoController.model.Post;
import org.springframework.stereotype.Service;

@Service
public class PostMapper {
    public PostDTO toDto(Post post) {
        PostDTO dto = new PostDTO();
        dto.setPostId(post.getPostId());
        dto.setTitulo(post.getTitulo());
        dto.setContenido(post.getContenido());

        if (post.getAutor() != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setNombre(post.getAutor().getNombre());
            usuarioDTO.setCorreo(post.getAutor().getCorreo());
            dto.setAutor(usuarioDTO);
        }

        return dto;
    }
}
