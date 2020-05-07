package org.cloudplayer.neetwords.service;

import java.util.List;

public interface CollectionService {
    List<String> getNameByUId(int id);
    List<String> getNameOByUId(int id);
    void update(List<String> collectionList, int id);
}
