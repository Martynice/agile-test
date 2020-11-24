package com.agile.test.dao;

import com.agile.test.model.Image;
import java.util.List;
import java.util.Map;

public interface ImageDao {
    Image add(Image image);

    List<Image> getByParameter(Map<String, String[]> parameters);

    void clear();
}
