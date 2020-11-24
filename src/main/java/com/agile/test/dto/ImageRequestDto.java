package com.agile.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImageRequestDto {
    private String id;
    @JsonProperty("cropped_picture")
    private String croppedImage;
}
