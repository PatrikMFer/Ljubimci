package com.ferovac.backend.Service;

import com.ferovac.backend.Entity.Ljubimac;
import com.ferovac.backend.Repository.LjubimacRepository;
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
