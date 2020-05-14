package com.book.controller;

import com.book.base.BaseResponse;
import com.book.model.TBook;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public BaseResponse<List<TBook>> getBookList(@RequestParam("name") String name){
        return bookService.getBookList(name);
    }

    @GetMapping("{id}")
    public BaseResponse getBookById(@PathVariable Integer id){
        return bookService.getByBookId(id);
    }
}
