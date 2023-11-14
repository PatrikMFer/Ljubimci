package com.ferovac.backend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/igracke")
public class IgrackaController {

    @Autowired
    private IgrackaService igrackaService;

    @GetMapping
    public List<Igracka> dohvatiSveIgracke() {
        return igrackaService.dohvatiSveIgracke();
    }
}

