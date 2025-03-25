package eu.campuchan.MongoController.dto;

import eu.campuchan.MongoController.model.Post;
import eu.campuchan.MongoController.model.Usuario;

public class PostDTO {

    private Long postId;
    private String titulo;
    private String contenido;
    private UsuarioDTO autor;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public UsuarioDTO getAutor() {
        return autor;
    }

    public void setAutor(UsuarioDTO autor) {
        this.autor = autor;
    }

    public PostDTO convertToDto(Post post) {
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
