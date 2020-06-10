package org.cloudplayer.neetwords.controller;

import org.cloudplayer.neetwords.pojo.Book;
import org.cloudplayer.neetwords.service.BookService;
import org.cloudplayer.neetwords.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	/**
	 * 获取单词表列表
	 * @return
	 */
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> getBookList(){
		List<Book> bookList = bookService.getBookList();
		Map<String, Object> res = new HashMap<>();
		res.put("bookList", bookList);
		return ResponseEntity.ok(JsonUtil.success(res));
	}

	/**
	 * 下载对应单词书
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@GetMapping("{id}")
	public ResponseEntity<Map<String, Object>> getBookById(@PathVariable Integer id, HttpServletResponse response) throws IOException {
		Book book = bookService.getByBookById(id);
		response.sendRedirect(book.getCdnAddr());
		Map<String, Object> res = new HashMap<>();
		res.put("cdn", book.getCdnAddr());

		return ResponseEntity.ok(JsonUtil.success(res));
	}
}
