package org.cloudplayer.neetwords.service;

import org.cloudplayer.neetwords.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

	private static final String COLLECTION_NAME = "book";

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Book> getBookList() {
		return mongoTemplate.findAll(Book.class, COLLECTION_NAME);
	}

	@Override
	public Book getByBookById(int id) {
		Query query = new Query(Criteria.where("bookId").is(id));
		return mongoTemplate.findOne(query,Book.class, COLLECTION_NAME);
	}
}
