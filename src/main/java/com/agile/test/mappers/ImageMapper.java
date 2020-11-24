package com.agile.test.mappers;

import com.agile.test.dto.ImageRequestDto;
import com.agile.test.dto.ImageResponseDto;
import com.agile.test.model.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public ImageResponseDto toResponseDto(Image image) {
        ImageResponseDto imageResponseDto = new ImageResponseDto();
        imageResponseDto.setId(image.getId());
        imageResponseDto.setLink(image.getLink());
        return imageResponseDto;
    }

    public ImageRequestDto fromRequestDto(Image image) {
        ImageRequestDto imageRequestDto = new ImageRequestDto();
        imageRequestDto.setId(image.getId());
        imageRequestDto.setCroppedImage(image.getLink());
        return imageRequestDto;
    }
}
