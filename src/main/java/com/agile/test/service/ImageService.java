package com.agile.test.service;

import com.agile.test.model.Image;
import java.util.List;
import java.util.Map;

public interface ImageService {
    Image add(Image image);

    List<Image> getByParameter(Map<String, String[]> parameters);

    List<Image> getAll();

    void clear();
}
