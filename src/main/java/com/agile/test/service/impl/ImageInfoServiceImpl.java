package com.agile.test.service.impl;

import com.agile.test.dao.ImageInfoDao;
import com.agile.test.model.ImageInfo;
import com.agile.test.service.ImageInfoService;
import org.springframework.stereotype.Service;

@Service
public class ImageInfoServiceImpl implements ImageInfoService {
    private final ImageInfoDao imageInfoDao;

    public ImageInfoServiceImpl(ImageInfoDao imageInfoDao) {
        this.imageInfoDao = imageInfoDao;
    }

    public ImageInfo add(ImageInfo imageInfo) {
        return imageInfoDao.add(imageInfo);
    }
}
