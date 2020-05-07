package org.cloudplayer.neetwords.mapper;

import com.example.zhaidict.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select ur_id as id,ur_name as name,ur_password as password,ur_avatar as avatar, " +
            "ur_daily_amount as amonutPerDay, ur_bo_id as bookId, ur_change_times as timesToChangeBackground, " +
            "ur_keep_time as durationKeepAfterRecite, ur_tip_time as tipsDuration from t_user where ur_id=#{id}")
    User getUserById(int id);

    @Update("update t_user set ur_daily_amount = #{amonutPerDay}, ur_bo_id = #{bookId}, " +
            "ur_change_times = #{timesToChangeBackground}, ur_keep_time = #{durationKeepAfterRecite}, " +
            "ur_tip_time = #{tipsDuration} where ur_id=#{id}")
    void update(User user);
}
