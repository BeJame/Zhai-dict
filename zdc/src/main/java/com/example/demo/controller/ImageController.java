package com.example.demo.controller;

import com.example.demo.mapper.ImageMapper;
import com.example.demo.mapper.ImageTypeMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.pojo.Image;
import com.example.demo.pojo.ImageType;
import com.example.demo.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ImageMapper imageMapper;
    @Autowired
    private ImageTypeMapper imageTypeMapper;

    @RequestMapping("/images")
    public Map listStudent(@RequestParam(value="type", required=true)String type) {//和前端配合时加入,@RequestBody int uid
        Map<String, Object> returnMap=new HashMap<String, Object>();
        returnMap.put("code",200);

        int uid=1;//和前端配合时，用户uid 由前端获得

        //type=list
        if (type.equals("list")){
            List<ImageType> imageType = imageTypeMapper.findAllTypes(uid);

            List<Map> list=new ArrayList<>();
            for (int i=0;i<imageType.size();i++){
                Map<String, Object> theMap=new HashMap<>();
                theMap.put("type",imageType.get(i).getITy_name());
                theMap.put("description",imageType.get(i).getTy_description());
                list.add(theMap);
            }
            returnMap.put("data",list);
            //结果如：{"code":200,"data":[{"description":"美丽的风景","type":"风景"},{"description":"可爱的宠物","type":"宠物"}]}
        }
        else{

            ImageType imt=imageTypeMapper.findByTypeName(uid,type);//指定类型的图片类型信息

            List<Image> images=imageMapper.findImageByType(imt.getTy_id());//统一类型的所有图

            List<String> urls=new ArrayList<>();//用于记录统一类型图片的所有URL
            for (int i=0;i<images.size();i++){
                urls.add(images.get(i).getIm_url());
            }

            Map<String,Object> tempMap=new HashMap<>();
            tempMap.put("type",imt.getITy_name());
            tempMap.put("description",imt.getTy_description());
            tempMap.put("urls",urls);
            returnMap.put("data",tempMap);
        }
        return returnMap;
    }
}
