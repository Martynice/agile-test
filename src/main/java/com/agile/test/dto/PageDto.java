package com.agile.test.dto;

import java.util.List;
import lombok.Data;

@Data
public class PageDto {
    private Integer page;
    private Long pageNumber;
    private Boolean isMore;
    private List<ImageDto> images;
}
