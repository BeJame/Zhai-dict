package org.cloudplayer.neetwords.service;

import org.cloudplayer.neetwords.mapper.UserMapper;
import org.cloudplayer.neetwords.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(int id){
        return userMapper.getUserById(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }
}
