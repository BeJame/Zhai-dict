package org.cloudplayer.neetwords.mapper;

import com.example.zhaidict.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookMapper {
    @Select("select bo_id as id,bo_name as name, bo_num as num, bo_img_addr as address from t_book where bo_id=#{id}")
    Book get(int id);
}
