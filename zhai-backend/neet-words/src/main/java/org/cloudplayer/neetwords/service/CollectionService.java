package org.cloudplayer.neetwords.service;

import org.cloudplayer.neetwords.pojo.Collection;


public interface CollectionService {
    void saveCollection(Collection collection);
    Collection getCollection(String userId);
}
