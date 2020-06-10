package org.cloudplayer.neetwords.service;

import org.cloudplayer.neetwords.pojo.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
public class CollectionServiceImpl implements CollectionService {

    private final static String COLLECTION_NAME="collection";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveCollection(Collection collection) {
        mongoTemplate.save(collection, COLLECTION_NAME);
    }

    @Override
    public Collection getCollection(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, Collection.class, COLLECTION_NAME);
    }
}
