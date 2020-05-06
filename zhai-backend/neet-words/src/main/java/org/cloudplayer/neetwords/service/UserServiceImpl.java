package org.cloudplayer.neetwords.service;

import com.example.zhaidict.mapper.UserMapper;
import com.example.zhaidict.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    public User get(int id){
        return userMapper.get(id);
    }

    public void update(User user){
        userMapper.update(user);
    }
}
