package eu.campuchan.MongoController.service;

import eu.campuchan.MongoController.model.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Long generateSequence(String sequenceName) {
        Query query = new Query(Criteria.where("_id").is(sequenceName));
        Update update = new Update().inc("value", 1);
        FindAndModifyOptions options = new FindAndModifyOptions()
                .returnNew(true)
                .upsert(true);

        Sequence sequence = mongoTemplate.findAndModify(
                query,
                update,
                options,
                Sequence.class
        );

        return (sequence != null && sequence.getValue() != null)
                ? sequence.getValue()
                : 1L;
    }
}
