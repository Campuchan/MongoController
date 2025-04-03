package eu.campuchan.MongoController.dto;

import eu.campuchan.MongoController.model.Comentario;


public class ComentarioMapper {

    private Long postId;
    private String contenido;
    private UsuarioDTO autor;

    public Long getId() {
        return postId;
    }

    public void setId(Long postId) {
        this.postId = postId;
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

    public ComentarioMapper convertToDto(Comentario comentario) {
        ComentarioMapper dto = new ComentarioMapper();
        dto.setPostId(comentario.getId());
        dto.setContenido(comentario.getContenido());

        if (comentario.getAutor() != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setNombre(comentario.getAutor().getNombre());
            usuarioDTO.setCorreo(comentario.getAutor().getCorreo());
            dto.setAutor(usuarioDTO);
        }

        return dto;
    }
}
