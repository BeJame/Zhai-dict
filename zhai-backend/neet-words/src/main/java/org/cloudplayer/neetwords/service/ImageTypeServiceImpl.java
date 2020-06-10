package org.cloudplayer.neetwords.service;

import org.cloudplayer.neetwords.pojo.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageTypeServiceImpl implements ImageTypeService {

    private static final String COLLECTION_NAME = "image";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ImageType> getTypeList() {
        return mongoTemplate.findAll(ImageType.class, COLLECTION_NAME);
    }

    @Override
    public ImageType getType(String name) {
	    Query query = new Query(Criteria.where("name").is(name));
	    return mongoTemplate.findOne(query, ImageType.class, COLLECTION_NAME);
    }
}
