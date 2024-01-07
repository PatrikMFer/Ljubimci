package com.ferovac.backend.Controller;

import com.ferovac.backend.Entity.Ljubimac;
import com.ferovac.backend.Entity.Vlasnik;
import com.ferovac.backend.Service.LjubimacService;
import com.ferovac.backend.Service.VlasnikService;
import com.ferovac.backend.dto.LjubimacRequest;
import com.ferovac.backend.dto.LjubimacResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ljubimci")
public class LjubimacController {

    private final LjubimacService ljubimacService;
    private final VlasnikService vlasnikService;

    @Autowired
    public LjubimacController(LjubimacService ljubimacService,  VlasnikService vlasnikService) {
        this.ljubimacService = ljubimacService;
        this.vlasnikService = vlasnikService;
    }
    //  Get za dohvacanje cijele kolekcije
    @GetMapping("/ljubimciIVlasnici")
    public ResponseEntity<List<LjubimacResponse>> getLjubimciWithVlasnici() {
        List<Ljubimac> ljubimci = ljubimacService.getAllLjubimci();
        List<LjubimacResponse> ljubimacResponses = new ArrayList<>();

        for (Ljubimac ljubimac : ljubimci) {
            Vlasnik vlasnik = ljubimac.getVlasnik();
            LjubimacResponse ljubimacResponse = new LjubimacResponse(ljubimac, vlasnik);
            ljubimacResponses.add(ljubimacResponse);
        }

        return ResponseEntity.ok(ljubimacResponses);
    }

    @GetMapping("/ljubimciIVlasniciJSON")
    public ResponseEntity<List<Ljubimac>> getAllLjubimci() {
        List<Ljubimac> ljubimci = ljubimacService.getAllLjubimci();
        return ResponseEntity.ok(ljubimci);
    }


    //  Get za dohvacanje pojedinačnog resursa iz kolekcije
    @GetMapping("/id/{idLjubimca}")
    public ResponseEntity<LjubimacResponse> getLjubimacById(@PathVariable Long idLjubimca) {
        Ljubimac ljubimac = ljubimacService.getLjubimacById(idLjubimca);
        LjubimacResponse ljubimacResponse = LjubimacResponse.fromLjubimac(ljubimac);
        return ResponseEntity.ok(ljubimacResponse);
    }

    //  1. Get po vlastitom izboru dohvaca sve ljubimce po adresi
    @GetMapping("/adresa/{adresaLjubimca}")
    public ResponseEntity<List<LjubimacResponse>> getLjubimciByAdresa(@PathVariable String adresaLjubimca) {
        List<Ljubimac> ljubimci = ljubimacService.getLjubimciByAdresa(adresaLjubimca);

        List<LjubimacResponse> ljubimacResponses = ljubimci.stream()
                .map(LjubimacResponse::fromLjubimac)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ljubimacResponses);
    }

    //  2. Get po vlastitom izboru dohvaca sve ljubimce po imenu
    @GetMapping("/ime/{imeLjubimca}")
    public ResponseEntity<List<LjubimacResponse>> getLjubimciByIme(@PathVariable String imeLjubimca) {
        List<Ljubimac> ljubimci = ljubimacService.getLjubimciByIme(imeLjubimca);

        List<LjubimacResponse> ljubimacResponses = ljubimci.stream()
                .map(LjubimacResponse::fromLjubimac)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ljubimacResponses);
    }


    //  3. Get po vlastitom izboru dohvaca sve ljubimce po vrsti
    @GetMapping("/vrsta/{vrstaLjubimca}")
    public ResponseEntity<List<LjubimacResponse>> getLjubimciByVrsta(@PathVariable String vrstaLjubimca) {
        List<Ljubimac> ljubimci = ljubimacService.getLjubimciByVrsta(vrstaLjubimca);

        List<LjubimacResponse> ljubimacResponses = ljubimci.stream()
                .map(LjubimacResponse::fromLjubimac)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ljubimacResponses);
    }

    //  Post za ubacivanje pojedinačnog resursa u kolekciju
    @PostMapping
    public ResponseEntity<Ljubimac> createLjubimacWithVlasnik(@RequestBody LjubimacRequest ljubimacRequest) {
        Vlasnik vlasnik = vlasnikService.createVlasnik(ljubimacRequest.toVlasnik());
        Ljubimac ljubimac = ljubimacService.createLjubimac(ljubimacRequest.toLjubimac(), vlasnik);
        return ResponseEntity.ok(ljubimac);
    }

    //  Put za će se osvježivanje elemenata resursa
    @PutMapping("/{id}")
    public ResponseEntity<Ljubimac> updateLjubimacWithVlasnik(@PathVariable Long id, @RequestBody LjubimacRequest ljubimacRequest) {
        Ljubimac updatedLjubimac = ljubimacService.updateLjubimac(id, ljubimacRequest);
        return ResponseEntity.ok(updatedLjubimac);
    }

    //  Delete za brisanje pojedinog resursa iz kolekcije temeljem
    //  jedinstvenog identifikatora resursa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLjubimac(@PathVariable Long id) {
        ljubimacService.deleteLjubimac(id);
        return ResponseEntity.noContent().build();
    }
}