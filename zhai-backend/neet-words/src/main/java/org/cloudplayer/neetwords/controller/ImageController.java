package org.cloudplayer.neetwords.controller;

import org.cloudplayer.neetwords.pojo.ImageType;
import org.cloudplayer.neetwords.service.ImageTypeService;
import org.cloudplayer.neetwords.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ImageController {

    @Autowired
    private ImageTypeService imageTypeService;


    /**
     * 获取图片类型
     * @param type list(获取单词列表)|其余图片类型
     * @return
     */
    @RequestMapping("/images")
    public ResponseEntity listStudent(@RequestParam String type) {
        Map<String, Object> res = new HashMap<>();

        //type=list
        if (type.equals("list")){
            List<ImageType> imageType = imageTypeService.getTypeList();
            res.put("typeList", imageType);
        } else{ //type=name
            ImageType imageType = imageTypeService.getType(type);//指定类型的图片类型信息
	        res.put("imageType", imageType);
        }
        return ResponseEntity.ok(JsonUtil.success(res));
    }
}
