package eu.campuchan.MongoController.model;

import org.springframework.data.annotation.Id;

public class Sequence {
    @Id
    private String id;

    private Long value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
