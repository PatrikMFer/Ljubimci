package com.ferovac.backend.Service;

import com.ferovac.backend.Entity.Ljubimac;
import com.ferovac.backend.Entity.Vlasnik;
import com.ferovac.backend.Repository.LjubimacRepository;
import com.ferovac.backend.Repository.VlasnikRepository;
import com.ferovac.backend.dto.LjubimacRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.*;
import java.util.List;

@Service
public class LjubimacService {

    private final LjubimacRepository ljubimacRepository;
    private final VlasnikRepository vlasnikRepository;
    private final VlasnikService vlasnikService;

    @Autowired
    public LjubimacService(LjubimacRepository ljubimacRepository, VlasnikService vlasnikService, VlasnikRepository vlasnikRepository) {
        this.ljubimacRepository = ljubimacRepository;
        this.vlasnikService = vlasnikService;
        this.vlasnikRepository = vlasnikRepository;
    }

    public List<Ljubimac> getAllLjubimci() {
        return ljubimacRepository.findAll();
    }

    public Ljubimac getLjubimacById(Long id) {
        return ljubimacRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ljubimac s ID-om " + id + " nije pronađen."));
    }


    public List<Ljubimac> getLjubimciByAdresa(String adresa) {
        return ljubimacRepository.findByAdresa(adresa);
    }

    public List<Ljubimac> getLjubimciByIme(String ime) {
        return ljubimacRepository.findByIme(ime);
    }

    public List<Ljubimac> getLjubimciByVrsta(String vrsta) {
        return ljubimacRepository.findByVrsta(vrsta);
    }

    public Ljubimac createLjubimac(Ljubimac ljubimac, Vlasnik vlasnik) {
        ljubimac.setVlasnik(vlasnik);
        return ljubimacRepository.save(ljubimac);
    }


    public Ljubimac updateLjubimac(Long id, LjubimacRequest ljubimacRequest) {
        Ljubimac ljubimac = ljubimacRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ljubimac s ID-om " + id + " nije pronađen."));

        Vlasnik vlasnik = vlasnikRepository.findById(ljubimac.getVlasnik().getId())
                .orElseThrow(() -> new EntityNotFoundException("Vlasnik ljubimca nije pronađen."));

        ljubimac.setIme(ljubimacRequest.getImeLjubimac());
        ljubimac.setVrsta(ljubimacRequest.getVrsta());
        ljubimac.setSpol(ljubimacRequest.getSpol());
        ljubimac.setDob(ljubimacRequest.getDob());
        ljubimac.setBoja(ljubimacRequest.getBoja());
        ljubimac.setPrehrana(ljubimacRequest.getPrehrana());
        ljubimac.setAdresa(ljubimacRequest.getAdresa());
        ljubimac.setVeterinar(ljubimacRequest.getVeterinar());

        vlasnik.setIme(ljubimacRequest.getImeVlasnika());
        vlasnik.setPrezime(ljubimacRequest.getPrezimeVlasnika());

        ljubimacRepository.save(ljubimac);
        vlasnikRepository.save(vlasnik);

        return ljubimac;
    }

    public void deleteLjubimac(Long idLjubimac) {
        Ljubimac ljubimac = getLjubimacById(idLjubimac);

        vlasnikService.deleteVlasnik(ljubimac.getVlasnik().getId());

        ljubimacRepository.deleteById(idLjubimac);
    }


}