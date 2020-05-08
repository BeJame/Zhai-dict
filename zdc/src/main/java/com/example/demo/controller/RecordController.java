package com.example.demo.controller;

import com.example.demo.mapper.RecordMapper;
import com.example.demo.pojo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RecordController {
    @Autowired
    private RecordMapper recordMapper;
    @RequestMapping("/record")
    public Map getRecord(){
                         //@RequestParam(value="count", required=false) int count,
                         //@RequestParam(value="page", required=false)int page){//和前端配合时加入,@RequestBody int uid
        int uid=1;//假设用户id为1
        Map<String, Object> returnMap=new HashMap<String, Object>();
        returnMap.put("code",200);

        List<Record> records= recordMapper.findAll(uid);
        List<Map> newList=new ArrayList<>();
        for (int i=0;i<records.size();i++){
            Map<String,Object> theMap=new HashMap();
            theMap.put("word",records.get(i).getRe_word());
            theMap.put("date",records.get(i).getRe_date());
            theMap.put("level",records.get(i).getRe_level());
            newList.add(theMap);
        }

        Map<String,Object> map=new HashMap();
        map.put("list",newList);
        returnMap.put("data",map);
        return returnMap;
    }
}

