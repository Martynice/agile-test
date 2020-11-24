package com.agile.test.controllers;

import com.agile.test.dto.ImageResponseDto;
import com.agile.test.dto.PageDto;
import com.agile.test.mappers.ImageMapper;
import com.agile.test.service.ImageService;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImageController {
    public static final String PAGE = "page";
    private final ImageMapper imageMapper;
    private final ImageService imageService;

    public ImageController(ImageMapper imageMapper, ImageService imageService) {
        this.imageMapper = imageMapper;
        this.imageService = imageService;
    }

    @GetMapping("/all")
    public PageDto getImages() {
        return imageMapper.toPageDto(imageService.getAll());
    }

    @GetMapping
    public PageDto getImagesFromPage(@RequestParam int page) {
        return imageMapper.toPageDto(imageService.getByParameter(
                Map.of(PAGE, new String[]{String.valueOf(page)})));
    }

    @GetMapping("/${id}")
    public ImageResponseDto getPhotoDetails(@PathVariable String id) {
        return imageMapper.toImageResponseDto(
                imageService.getByParameter(Map.of("id", new String[]{id})).get(0));
    }
}
