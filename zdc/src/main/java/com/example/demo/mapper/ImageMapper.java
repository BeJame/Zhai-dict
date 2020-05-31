package com.example.demo.mapper;

import com.example.demo.pojo.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ImageMapper {
    @Select("SELECT * FROM t_image")
    List<Image> findAll();

    @Select("SELECT * FROM t_image WHERE im_ty_id=#{typeId}")
    List<Image> findImageByType(int typeId);



}
