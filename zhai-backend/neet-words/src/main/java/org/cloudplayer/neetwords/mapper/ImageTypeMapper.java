package org.cloudplayer.neetwords.mapper;

import org.cloudplayer.neetwords.pojo.ImageType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageTypeMapper {
    @Select("select ty_id as id, ty_name as name, ty_description as description" +
            " from t_image_type where ty_ur_id = #{id} and ty_status = 0")
    List<ImageType> getNameByUId(int id);  //含有status的查询

    @Select("select ty_id as id, ty_name as name, ty_description as description" +
            " from t_image_type where ty_ur_id = #{id}")
    List<ImageType> getNameOByUId(int id); //不含status的查询

    @Select("select max(ty_id) from t_image_type")
    int getMaxId();

    @Insert("insert into t_image_type(ty_id,ty_name,ty_description,ty_ur_id,ty_status) values (#{imageType.id},#{imageType.name}," +
            "#{imageType.description},#{imageType.user.id},#{imageType.status})")
    void add(@Param("imageType") ImageType imageType);

    @Update("update t_image_type set ty_status = #{status} where ty_name = #{name} and ty_ur_id = #{user.id}")
    void update(ImageType imageType);

}
