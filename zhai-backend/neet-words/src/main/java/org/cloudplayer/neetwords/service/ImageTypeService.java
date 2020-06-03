package org.cloudplayer.neetwords.service;



import org.cloudplayer.neetwords.pojo.ImageType;

import java.awt.*;
import java.util.List;

public interface ImageTypeService {
    List<ImageType> getTypeList();

    ImageType getType(String name);
}
