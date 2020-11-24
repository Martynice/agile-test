package com.agile.test.service.impl;

import com.agile.test.dao.ImageDao;
import com.agile.test.model.Image;
import com.agile.test.service.ImageService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private static final String FIRST_PAGE = "1";
    private static final String PAGE = "page";
    private final ImageDao imageDao;

    public ImageServiceImpl(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Override
    public Image add(Image image) {
        return imageDao.add(image);
    }

    @Override
    public List<Image> getByParameter(Map<String, String[]> parameters) {
        return imageDao.getByParameter(parameters);
    }

    @Override
    public List<Image> getAll() {
        Map<String, String[]> defaultMap = new HashMap<>();
        defaultMap.put(PAGE, new String[]{FIRST_PAGE});
        return imageDao.getByParameter(defaultMap);
    }

    @Override
    public void clear() {
        imageDao.clear();
    }
}
