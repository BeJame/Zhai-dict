package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface FinishMapper {

    @Insert("INSERT INTO t_finish (fi_count,fi_ur_id) VALUES (#{0},#{1})")
    int addFinish(int count,int uid);
}
