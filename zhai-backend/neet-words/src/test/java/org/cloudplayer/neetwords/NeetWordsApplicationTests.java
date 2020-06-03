package org.cloudplayer.neetwords;

import org.bson.types.ObjectId;
import org.cloudplayer.neetwords.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


@SpringBootTest
public class NeetWordsApplicationTests {

	@Autowired
	private MongoTemplate mongoTemplate;


	@Test
	public void ConnectTest() {
		Query query = new Query(Criteria.where("_id").is(new ObjectId("5ed3717a4c443f01ae46fee8")));
		User result = mongoTemplate.findOne(query, User.class, "user");
		System.out.println(result == null);
//		System.out.println(result.getId() == null);
//		System.out.println(result.getName()+" "+result.getId());
	}
}
