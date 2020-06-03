package org.cloudplayer.neetwords.service;

import org.cloudplayer.neetwords.pojo.Book;

import java.util.List;

public interface BookService {
	List<Book> getBookList();

	Book getByBookById(int id);
}
