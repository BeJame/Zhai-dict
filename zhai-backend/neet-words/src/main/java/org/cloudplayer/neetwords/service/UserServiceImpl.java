package org.cloudplayer.neetwords.service;

import org.cloudplayer.neetwords.pojo.Configuration;
import org.cloudplayer.neetwords.pojo.RecordList;
import org.cloudplayer.neetwords.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String COLLECTION_NAME = "user";

    private static final String COLLECTION_RECORD = "record";

    private static final String COLLECTION_CONFIG = "config";

    @Override
    public User getUserById(String id){
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), User.class, COLLECTION_NAME);
    }

	@Override
	public void insert(User user) {
		mongoTemplate.insert(user,COLLECTION_NAME);
	}

	@Override
    public void update(User user) {
		mongoTemplate.save(user, COLLECTION_NAME);
    }

	@Override
	public Configuration getConfig(String userId) {
		Query query = new Query(Criteria.where("userId").is(userId));
		return mongoTemplate.findOne(query, Configuration.class, COLLECTION_CONFIG);
	}

	@Override
	public void saveConfig(Configuration config) {
		mongoTemplate.save(config, COLLECTION_CONFIG);
	}

	@Override
	public void finish(int count, String id) {
    	Query query = new Query(Criteria.where("_id").is(id));
		Update update = new Update().inc("finishCount", count);
		mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
	}

	@Override
	public void record(RecordList recordList) {
		mongoTemplate.save(recordList, COLLECTION_NAME);
	}

	@Override
	public RecordList getRecord(String userId) {
		Query query = new Query(Criteria.where("userId").is(userId));
    	return mongoTemplate.findOne(query, RecordList.class, COLLECTION_RECORD);
	}
}
