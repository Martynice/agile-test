package com.agile.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImageDto {
    private String id;
    @JsonProperty("cropped_picture")
    private String croppedImage;
}
