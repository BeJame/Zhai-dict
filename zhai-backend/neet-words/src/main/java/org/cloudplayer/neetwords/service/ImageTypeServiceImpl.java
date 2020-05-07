package org.cloudplayer.neetwords.service;

import org.cloudplayer.neetwords.mapper.ImageTypeMapper;
import org.cloudplayer.neetwords.pojo.ImageType;
import org.cloudplayer.neetwords.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageTypeServiceImpl implements ImageTypeService {
    @Autowired
    private ImageTypeMapper imageTypeMapper;

    @Override
    public List<String> getNameByUId(int id){
        List<ImageType> imageTypeList = imageTypeMapper.getNameByUId(id);
        List<String> imageTypeNameList = imageTypeList.stream().map(ImageType::getName).collect(Collectors.toList());
        return imageTypeNameList;
    }

    @Override
    public List<String> getNameOByUId(int id){
        List<ImageType> imageTypeList = imageTypeMapper.getNameOByUId(id);
        List<String> imageTypeNameList = imageTypeList.stream().map(ImageType::getName).collect(Collectors.toList());
        return imageTypeNameList;
    }

    @Override
    public void update(List<String> imageTypeList,int id) {
         //读取数据库中保存的图片类型的名字
        List<String> nameList = getNameByUId(id);  //状态为0的图片类型列表
        boolean flag = false;
        //先把所有的收藏图片类型状态设为1
        for (int i = 0; i<nameList.size();i++){
            ImageType imageType = new ImageType();
            User user = new User();
            user.setId(id);
            imageType.setName(nameList.get(i));
            imageType.setUser(user);
            imageType.setStatus(1);
            imageTypeMapper.update(imageType);
        }
        List<String> oNameList = getNameOByUId(id);  //不分状态的图片类型列表
        for (int i = 0;i<imageTypeList.size();i++){
            ImageType imageType = new ImageType();
            User user = new User();
            for (int j = 0; j<oNameList.size();j++){
                if (oNameList.get(j).equals(imageTypeList.get(i))){
                    flag = true;
                    break;
                }
            }
            //如果前端传回的数据在数据库中存在，数据库中图片类型状态置0，否则添加一条新记录
            if (flag){
                user.setId(id);
                imageType.setName(imageTypeList.get(i));
                imageType.setUser(user);
                imageType.setStatus(0);
                imageTypeMapper.update(imageType);
            }else{
                user.setId(id);
                imageType.setId(imageTypeMapper.getMaxId()+1);
                imageType.setName(imageTypeList.get(i));
                imageType.setUser(user);
                imageType.setStatus(0);
                imageTypeMapper.add(imageType);
            }
            flag = false;
        }

    }
}
