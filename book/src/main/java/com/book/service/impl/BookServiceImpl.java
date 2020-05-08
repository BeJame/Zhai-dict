package com.book.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.book.base.BaseResponse;
import com.book.mapper.BookMapper;
import com.book.mapper.RememberMapper;
import com.book.model.Remember;
import com.book.model.TBook;
import com.book.service.BookService;
import com.book.utils.JsonUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RememberMapper rememberMapper;

    @Override
    public BaseResponse<List<TBook>> getBookList(String name) {
        List<TBook> books = bookMapper.selectByName(name);
        return BaseResponse.success(books);
    }

    @Override
    public BaseResponse getByBookId(Integer id) {
        // 1、单词库中查找匹配单词
        String words = JsonUtil.getJson();
        if (words == null){
            return BaseResponse.failed("单词库为空");
        }
        List<Remember> list = JSONObject.parseArray(words,  Remember.class);
        if (list.isEmpty()){
            return BaseResponse.failed("单词库为空");
        }
        if (id > list.size() - 1){
            return BaseResponse.failed("id为" + id + "的单词不存在");
        }
        Remember remember = list.get(id);
        List<Remember> remembers = rememberMapper.select(remember);
        if (!remembers.isEmpty()){
            return BaseResponse.failed("remember表已存在该单词");
        }
        // 2、存入数据库
        return rememberMapper.insertSelective(remember) > 0 ? BaseResponse.success() : BaseResponse.failed();
    }
}
