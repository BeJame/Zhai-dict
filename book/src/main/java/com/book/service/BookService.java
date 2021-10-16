package com.book.service;

import com.book.base.BaseResponse;
import com.book.model.TBook;

import java.util.List;

public interface BookService {

    BaseResponse<List<TBook>> getBookList(String name);

    BaseResponse getByBookId(Integer id);
}
