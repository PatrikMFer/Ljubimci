package com.ferovac.backend.Service;

import com.ferovac.backend.Entity.Igracka;
import com.ferovac.backend.Repository.IgrackaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IgrackaService {
    @Autowired
    private IgrackaRepository igrackaRepository;

    public List<Igracka> dohvatiSveIgracke() {
        return igrackaRepository.findAll();
    }
}