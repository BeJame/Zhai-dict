package com.example.demo.controller;

import com.example.demo.mapper.FinishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FinishController {

    @Autowired
    private FinishMapper finishMapper;

    @RequestMapping("/finish")
    public String addFinish(@RequestParam(value="count", required=true)int count){//和前端配合时加入,@RequestBody int uid
        int uid=1;//假设uid为1
        finishMapper.addFinish(count,uid);
        return "打卡成功";
    }
}
