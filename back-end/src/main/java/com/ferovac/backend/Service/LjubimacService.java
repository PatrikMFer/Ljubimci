package com.ferovac.backend.Service;

import com.ferovac.backend.Entity.Ljubimac;
import com.ferovac.backend.Entity.Vlasnik;
import com.ferovac.backend.Exception.ElementCreationException;
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

    public List<Ljubimac> pretraziLjubimceWildCard(String searchText) {
        if (searchText.isEmpty()) {
            return getAllLjubimci();
        }
        return ljubimacRepository.findByWildCardSearch(searchText);
    }


    public List<Ljubimac> pretraziLjubimcePoAtributu(String searchText, String attribute) {
        if (searchText.isEmpty()) {
            return getAllLjubimci();
        }

        switch (attribute) {
            case "idLjubimac":
                return ljubimacRepository.findByIdWildCardSearch(searchText);
            case "imeLjubimac":
                return ljubimacRepository.findByImeContainingIgnoreCase(searchText);
            case "vrsta":
                return ljubimacRepository.findByVrstaContainingIgnoreCase(searchText);
            case "spol":
                return ljubimacRepository.findBySpolContainingIgnoreCase(searchText);
            case "dob":
                return ljubimacRepository.findByDobWildCardSearch(searchText);
            case "boja":
                return ljubimacRepository.findByBojaContainingIgnoreCase(searchText);
            case "prehrana":
                return ljubimacRepository.findByPrehranaContainingIgnoreCase(searchText);
            case "adresa":
                return ljubimacRepository.findByAdresaContainingIgnoreCase(searchText);
            case "veterinar":
                return ljubimacRepository.findByVeterinarContainingIgnoreCase(searchText);
            case "imeVlasnika":
                return ljubimacRepository.findByVlasnik_ImeContainingIgnoreCase(searchText);
            case "prezimeVlasnika":
                return ljubimacRepository.findByVlasnik_PrezimeContainingIgnoreCase(searchText);
            default:
                throw new IllegalArgumentException("Nepoznat atribut: " + attribute);
        }
    }



    public Ljubimac createLjubimac(Ljubimac ljubimac, Vlasnik vlasnik) {
        try {
            ljubimac.setVlasnik(vlasnik);
            return ljubimacRepository.save(ljubimac);
        } catch (Exception ex) {
            throw new ElementCreationException("Nije moguće kreirati ljubimca");
        }
    }



    public Ljubimac updateLjubimac(Long id, LjubimacRequest ljubimacRequest) {
        Ljubimac ljubimac = ljubimacRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ljubimac s ID-om " + id + " nije pronađen"));

        Vlasnik vlasnik = vlasnikRepository.findById(ljubimac.getVlasnik().getId())
                .orElseThrow(() -> new EntityNotFoundException("Vlasnik ljubimca nije pronađen"));

        vlasnik.setIme(ljubimacRequest.getImeVlasnika());
        vlasnik.setPrezime(ljubimacRequest.getPrezimeVlasnika());

        ljubimac.setIme(ljubimacRequest.getImeLjubimac());
        ljubimac.setVrsta(ljubimacRequest.getVrsta());
        ljubimac.setSpol(ljubimacRequest.getSpol());
        ljubimac.setDob(ljubimacRequest.getDob());
        ljubimac.setBoja(ljubimacRequest.getBoja());
        ljubimac.setPrehrana(ljubimacRequest.getPrehrana());
        ljubimac.setAdresa(ljubimacRequest.getAdresa());
        ljubimac.setVeterinar(ljubimacRequest.getVeterinar());

        vlasnikRepository.flush();
        ljubimacRepository.flush();


        return ljubimac;
    }


    public boolean deleteLjubimac(Long id) {
        Ljubimac ljubimac = getLjubimacById(id);

        if (ljubimac == null) {
            return false;
        }

        ljubimacRepository.deleteById(id);

        vlasnikService.deleteVlasnik(ljubimac.getVlasnik().getId());

        return true;
    }


}