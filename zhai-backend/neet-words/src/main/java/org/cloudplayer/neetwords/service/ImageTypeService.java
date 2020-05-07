package org.cloudplayer.neetwords.service;



import java.util.List;

public interface ImageTypeService {
    List<String> getNameByUId(int id);
    List<String> getNameOByUId(int id);
    void update(List<String> imageTypeList, int id);
}
