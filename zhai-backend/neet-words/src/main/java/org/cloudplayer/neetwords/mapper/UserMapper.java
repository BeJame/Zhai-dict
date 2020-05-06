package org.cloudplayer.neetwords.mapper;

import com.example.zhaidict.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select ur_id as id,ur_name as name,ur_password as password,ur_avatar as avatar," +
            " ur_daily_plan as plan, ur_learned as learned, ur_bo_id as bookid from t_user where ur_id=#{id}")
    User get(int id);

    @Update("update t_user set ur_name = #{name},ur_password=#{password},ur_avatar=#{avatar}," +
            "ur_daily_plan = #{plan}, ur_learned = #{learned}, ur_bo_id = #{bookid} where ur_id=#{id}")
    void update(User user);
}
