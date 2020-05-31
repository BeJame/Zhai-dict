package com.example.demo.mapper;

import com.example.demo.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RecordMapper {
    @Select("SELECT * FROM t_record WHERE re_ur_id=#{uid}")
    List<Record> findAll(int uid);
}
