package com.ferovac.backend.Controller;

import com.ferovac.backend.Entity.Ljubimac;
import com.ferovac.backend.Service.LjubimacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ljubimci")
public class LjubimacController {

    @Autowired
    private LjubimacService ljubimacService;

    @GetMapping
    public List<Ljubimac> dohvatiSveLjubimce() {
        return ljubimacService.dohvatiSveLjubimce();
    }
}
