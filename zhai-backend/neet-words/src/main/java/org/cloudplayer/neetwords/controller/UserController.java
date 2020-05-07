package org.cloudplayer.neetwords.controller;

import com.example.zhaidict.pojo.User;
import com.example.zhaidict.service.CollectionService;
import com.example.zhaidict.service.ImageTypeService;
import com.example.zhaidict.service.UserService;
import com.example.zhaidict.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ImageTypeService imageTypeService;
    @Autowired
    private CollectionService collectionService;

    @RequestMapping(value = "/config",produces = "application/json;charset=utf-8",method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getConfig(@RequestParam("id") Integer id){
        User user = userService.getUserById(id);
        List<String> imageTypeList = imageTypeService.getNameByUId(id);
        List<String> collectionList = collectionService.getNameByUId(id);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",user.getName());
        map.put("imageType",imageTypeList);
        map.put("collection",collectionList);
        return ResponseEntity.ok(JsonUtil.success(map));
    }

    @RequestMapping(value = "/config",produces = "application/json;charset=utf-8",method= RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> postConfig(@RequestBody Map<String,Object> map){
        User user = new User();
        int id = (int)map.get("id");
        user.setId(id);
        user.setAmonutPerDay((int)map.get("amonutPerDay"));
        user.setBookId((int)map.get("bookId"));
        user.setTimesToChangeBackground((int)map.get("timesToChangeBackground"));
        user.setDurationKeepAfterRecite((int)map.get("durationKeepAfterRecite"));
        user.setTipsDuration((int)map.get("tipsDuration"));
        List<String> imageTypeList = (List<String>)map.get("imageList");
        List<String> collectionList = (List<String>)map.get("collection");
        userService.update(user);
        imageTypeService.update(imageTypeList,id);
        collectionService.update(collectionList,id);
        return ResponseEntity.ok(JsonUtil.success());
    }

}
