package eu.campuchan.MongoController.service;

import eu.campuchan.MongoController.dto.PostDTO;
import eu.campuchan.MongoController.model.Post;

public class PostMapper {
    public PostDTO toDto(Post post) {
        PostDTO dto = new PostDTO();
        dto.setPostId(post.getPostId());
        dto.setTitulo(post.getTitulo());
        dto.setContenido(post.getContenido());

        if (post.getAutor() != null) {
            PostDTO.AuthorDTO authorDto = new PostDTO.AuthorDTO();
            authorDto.setNombre(post.getAutor().getNombre());
            authorDto.setCorreo(post.getAutor().getCorreo());
            dto.setAutor(authorDto);
        }

        return dto;
    }
}
