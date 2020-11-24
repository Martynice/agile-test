package com.agile.test.controllers;

import com.agile.test.model.Image;
import com.agile.test.service.ImageService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    private static final String AMPERSAND_SIGN = "&";
    private static final String EQUAL_SIGN = "=";
    private static final int FIRST_ARGUMENT = 0;
    private static final int SECOND_ARGUMENT = 1;
    private final ImageService imageService;

    public SearchController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/${searchTerm}")
    public List<Image> searchPhotoByParameter(@PathVariable String searchTerm) {
        Map<String, String[]> map = new HashMap<>();
        String[] terms = searchTerm.split(AMPERSAND_SIGN);
        for (String term : terms) {
            String[] split = term.split(EQUAL_SIGN);
            map.put(split[FIRST_ARGUMENT], new String[]{split[SECOND_ARGUMENT]});
        }
        return imageService.getByParameter(map);
    }
}
