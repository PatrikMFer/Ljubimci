package com.ferovac.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LjubimacService {
    @Autowired
    private LjubimacRepository ljubimacRepository;

    public List<Ljubimac> dohvatiSveLjubimce() {
        return ljubimacRepository.findAll();
    }
}
