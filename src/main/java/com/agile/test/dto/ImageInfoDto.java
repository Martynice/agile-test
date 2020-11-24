package com.agile.test.dto;

import lombok.Data;

@Data
public class ImageInfoDto {
    private String author;
    private String camera;
    private String tags;
    private ImageDto imageDto;
}
