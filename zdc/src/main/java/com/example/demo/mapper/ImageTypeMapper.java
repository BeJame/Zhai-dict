package com.example.demo.mapper;

import com.example.demo.pojo.Image;
import com.example.demo.pojo.ImageType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ImageTypeMapper {

    @Select("SELECT * FROM t_image_type WHERE ty_ur_id=#{uid}")
    List<ImageType> findAllTypes(int uid);

    @Select("SELECT * FROM t_image_type WHERE ty_ur_id=#{0} AND ty_name=#{1}")
    ImageType findByTypeName(int uid,String type);
}
