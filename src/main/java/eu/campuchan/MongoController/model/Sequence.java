package eu.campuchan.MongoController.model;

import org.springframework.data.annotation.Id;

public class Sequence {
    @Id
    private String id;

    private Long value = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getValue() {
        return this.value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
