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
    private List<Comentario> comentarios;

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
