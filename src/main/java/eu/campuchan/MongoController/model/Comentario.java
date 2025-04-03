package eu.campuchan.MongoController.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "comentario")
public class Comentario {
    @Id
    private String id;
    private String contenido;

    @DBRef
    private Usuario autor;

    @DBRef
    private Object parent;

    @DBRef
    private List<Comentario> comentarios;

    public Comentario(String contenido, Usuario autor, Object parent) {
        this.contenido = contenido;
        this.autor = autor;
        this.parent = parent;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentario(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
