package com.agile.test.controllers;

import com.agile.test.service.InjectDataService;
import org.springframework.web.bind.annotation.GetMapping;

public class InjectDataController {
    private final InjectDataService injectDataService;

    public InjectDataController(InjectDataService injectDataService) {
        this.injectDataService = injectDataService;
    }

    @GetMapping
    public void injectData() {
        injectDataService.injectData();
    }
}
