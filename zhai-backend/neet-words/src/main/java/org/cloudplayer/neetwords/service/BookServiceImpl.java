package org.cloudplayer.neetwords.service;

import com.example.zhaidict.mapper.BookMapper;
import com.example.zhaidict.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookMapper bookMapper;

    public Book get(int id){
        return bookMapper.get(id);
    }
}
