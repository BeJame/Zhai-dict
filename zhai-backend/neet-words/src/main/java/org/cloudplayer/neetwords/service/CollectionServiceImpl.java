package org.cloudplayer.neetwords.service;

import com.example.zhaidict.mapper.CollectionMapper;
import com.example.zhaidict.pojo.Collection;
import com.example.zhaidict.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public List<String> getNameByUId(int id) {
        List<Collection> collectionList = collectionMapper.getNameByUId(id);
        List<String> collectionNameList = collectionList.stream().map(Collection::getName).collect(Collectors.toList());
        return collectionNameList;
    }

    @Override
    public List<String> getNameOByUId(int id) {
        List<Collection> collectionList = collectionMapper.getNameOByUId(id);
        List<String> collectionNameList = collectionList.stream().map(Collection::getName).collect(Collectors.toList());
        return collectionNameList;
    }

    @Override
    public void update(List<String> collectionList, int id) {
        List<String> nameList = getNameByUId(id);  //状态为0的收藏单词列表
        boolean flag = false;
        //先把所有的收藏单词状态设为1
        for (int i = 0; i<nameList.size();i++){
            Collection collection = new Collection();
            User user = new User();
            user.setId(id);
            collection.setName(nameList.get(i));
            collection.setUser(user);
            collection.setStatus(1);
            collectionMapper.update(collection);
        }
        List<String> oNameList = getNameOByUId(id);  //不分状态的图片类型列表
        for (int i = 0;i<collectionList.size();i++){
            Collection collection = new Collection();
            User user = new User();
            for (int j = 0; j<oNameList.size();j++){
                if (oNameList.get(j).equals(collectionList.get(i))){
                    flag = true;
                    break;
                }
            }
            //如果前端传回的数据在数据库中存在，数据库中收藏单词状态置0，否则添加一条新记录
            if (flag){
                user.setId(id);
                collection.setName(collectionList.get(i));
                collection.setUser(user);
                collection.setStatus(0);
                collectionMapper.update(collection);
            }else{
                user.setId(id);
                collection.setId(collectionMapper.getMaxId()+1);
                collection.setName(collectionList.get(i));
                collection.setUser(user);
                collection.setStatus(0);
                collectionMapper.add(collection);
            }
            flag = false;
        }

    }
}
