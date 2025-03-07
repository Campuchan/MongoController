package eu.campuchan.MongoController.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class SequenceGeneratorService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Long generateSequence(String sequenceName)
}
