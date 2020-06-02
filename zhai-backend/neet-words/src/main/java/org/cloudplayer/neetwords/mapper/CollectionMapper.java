package org.cloudplayer.neetwords.mapper;

import org.cloudplayer.neetwords.pojo.Collection;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectionMapper {
    @Select("select wo_id as id, wo_name as name, wo_status as status from t_collect_word " +
            "where wo_ur_id = #{id} and wo_status = 0")
    List<Collection> getNameByUId(int id);  //含有status的查询

    @Select("select wo_id as id, wo_name as name, wo_status as status from t_collect_word " +
            "where wo_ur_id = #{id}")
    List<Collection> getNameOByUId(int id);  //不含status的查询

    @Select("select max(wo_id) from t_collect_word")
    int getMaxId();

    @Insert("insert into t_collect_word(wo_id,wo_name,wo_ur_id,wo_status) values (#{collection.id},#{collection.name}," +
            "#{collection.user.id},#{collection.status})")
    void add(@Param("collection") Collection collection);

    @Update("update t_collect_word set wo_status = #{status} where wo_name = #{name} and wo_ur_id = #{user.id}")
    void update(Collection collection);

}
