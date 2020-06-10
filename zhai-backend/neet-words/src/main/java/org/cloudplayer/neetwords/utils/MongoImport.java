package org.cloudplayer.neetwords.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.cloudplayer.neetwords.pojo.ImageType;

import java.util.ArrayList;
import java.util.List;


public class MongoImport {

	public static void main(String[] args) {
		List<String> landscapes = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("https://test-fe.obs.cn-east-2.myhuaweicloud.com/images/comic/");
			sb.append(i+1);
			sb.append(".jpg");

			landscapes.add(sb.toString());
		}


//		MongoClient mongoClient = MongoClients.create("mongodb://tiefblau:admin@localhost:27017/neet-words?authSource=neet-words&authMechanism=SCRAM-SHA-1");
		MongoClient mongoClient = MongoClients.create("mongodb://tiefblau:admin@47.97.115.41:27017/neet-words?authSource=neet-words&authMechanism=SCRAM-SHA-1");

		MongoDatabase database = mongoClient.getDatabase("neet-words");
		MongoCollection<Document> collection = database.getCollection("image");

//
//
//		ImageType imageType = new ImageType();
//		imageType.setUrls(landscapes);
//		imageType.setName("landscape");
//		imageType.setDescription("风景名胜");

		Document document = new Document("name", "neet").append("description", "二次元").append("urls", landscapes);

		collection.insertOne(document);
	}
}
