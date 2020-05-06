package com.book.mapper;


import com.book.model.TBook;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface BookMapper extends BaseMapper<TBook> {
    List<TBook> selectByName(String name);
}
