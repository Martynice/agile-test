package com.agile.test.mappers;

import com.agile.test.dto.ImageDto;
import com.agile.test.dto.ImageInfoDto;
import com.agile.test.dto.ImageResponseDto;
import com.agile.test.dto.PageDto;
import com.agile.test.model.Image;
import com.agile.test.model.ImageInfo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public List<Image> toImageList(PageDto pageDto) {
        List<Image> images = new ArrayList<>();
        for (int i = 0; i < pageDto.getImages().size(); i++) {
            Image image = new Image();
            image.setId(pageDto.getImages().get(i).getId());
            image.setLink(pageDto.getImages().get(i).getCroppedImage());
            image.setPage(pageDto.getPage());
            images.add(image);
        }
        return images;
    }

    public PageDto toPageDto(List<Image> images) {
        PageDto pageDto = new PageDto();
        pageDto.setPage(images.get(0).getPage());
        List<ImageDto> imageDtos = new ArrayList<>();
        for (Image image : images) {
            ImageDto imageDto = new ImageDto();
            imageDto.setCroppedImage(image.getLink());
            imageDto.setId(image.getId());
            imageDtos.add(imageDto);
        }
        pageDto.setImages(imageDtos);
        return pageDto;
    }

    public ImageResponseDto toImageResponseDto(Image image) {
        ImageResponseDto imageResponseDto = new ImageResponseDto();
        imageResponseDto.setId(image.getId());
        imageResponseDto.setLink(image.getLink());
        return imageResponseDto;
    }

    public ImageInfo toImageInfo(ImageInfoDto imageInfoDto) {
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setAuthor(imageInfoDto.getAuthor());
        imageInfo.setCamera(imageInfoDto.getCamera());
        imageInfo.setTags(imageInfoDto.getTags());
        return imageInfo;
    }
}
