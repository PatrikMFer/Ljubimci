package com.ferovac.backend.Service;

import com.ferovac.backend.Entity.Vlasnik;
import com.ferovac.backend.Exception.ElementCreationException;
import com.ferovac.backend.Repository.VlasnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.*;
import java.util.List;

@Service
public class VlasnikService {

    private final VlasnikRepository vlasnikRepository;

    @Autowired
    public VlasnikService(VlasnikRepository vlasnikRepository) {
        this.vlasnikRepository = vlasnikRepository;
    }

    public List<Vlasnik> getAllVlasnici() {
        return vlasnikRepository.findAll();
    }

    public Vlasnik getVlasnikById(Long vlasnikId) {
        return vlasnikRepository.findById(vlasnikId)
                .orElseThrow(() -> new EntityNotFoundException("Vlasnik s ID-om " + vlasnikId + " nije pronađen."));
    }

    public Vlasnik createVlasnik(Vlasnik vlasnik) {
        try {
            return vlasnikRepository.save(vlasnik);
        } catch (Exception ex) {
            throw new ElementCreationException("Nije moguće kreirati vlasnika");
        }
    }
    public void deleteVlasnik(Long vlasnikId) {
        vlasnikRepository.deleteById(vlasnikId);
    }
}
