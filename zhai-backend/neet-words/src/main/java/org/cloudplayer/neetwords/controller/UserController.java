package org.cloudplayer.neetwords.controller;

import com.example.zhaidict.pojo.Book;
import com.example.zhaidict.pojo.User;
import com.example.zhaidict.service.BookService;
import com.example.zhaidict.service.UserService;
import com.example.zhaidict.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * TODO 获取用户配置信息
     */
    @RequestMapping(value = "/config",produces = "application/json;charset=utf-8",method= RequestMethod.GET)
    @ResponseBody
    public String getConfig(@RequestParam("id")int id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = userService.get(id);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("countPerDay",user.getPlan());
        map.put("bookId",user.getBookid());
        map.put("image",user.getAvatar());
        String str = mapper.writeValueAsString(map);
        return str;
    }



    /**
     * TODO 修改用户配置
     */
    @RequestMapping(value = "/config",produces = "application/json;charset=utf-8",method= RequestMethod.POST)
    @ResponseBody
    public void updateConfig(@RequestBody User user){
        userService.update(user);
    }
}
