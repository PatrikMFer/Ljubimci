package com.ferovac.backend.Controller;

import com.ferovac.backend.Entity.Ljubimac;
import com.ferovac.backend.Entity.Vlasnik;
import com.ferovac.backend.Exception.ElementCreationException;
import com.ferovac.backend.Service.LjubimacService;
import com.ferovac.backend.Service.VlasnikService;
import com.ferovac.backend.dto.LjubimacRequest;
import com.ferovac.backend.dto.LjubimacResponse;
import com.ferovac.backend.dto.ApiResponseWrapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
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
    public ResponseEntity<ApiResponseWrapper<?>> getLjubimciWithVlasnici(
            @RequestParam(required = false, defaultValue = "") String searchText,
            @RequestParam(required = false, defaultValue = "all") String attribute
    ) {
        List<Ljubimac> ljubimci;

        if ("all".equals(attribute)) {
            ljubimci = ljubimacService.pretraziLjubimceWildCard(searchText);
        } else {
            ljubimci = ljubimacService.pretraziLjubimcePoAtributu(searchText, attribute);
        }

        List<LjubimacResponse> ljubimacResponses = new ArrayList<>();

        for (Ljubimac ljubimac : ljubimci) {
            Vlasnik vlasnik = ljubimac.getVlasnik();
            LjubimacResponse ljubimacResponse = new LjubimacResponse(ljubimac, vlasnik);
            ljubimacResponses.add(ljubimacResponse);
        }

        ljubimacResponses.sort(Comparator.comparing(LjubimacResponse::getIdLjubimac));

        if (ljubimacResponses.isEmpty()) {
            throw new EntityNotFoundException("Kolekcija je prazna");
        }

        ApiResponseWrapper<List<LjubimacResponse>> apiResponseWrapper = new ApiResponseWrapper<>("OK", "Dohvaćena je kolekcija ljubimaca", ljubimacResponses);
        return ResponseEntity.ok(apiResponseWrapper);
    }




    @GetMapping("/ljubimciIVlasniciJSON")
    public ResponseEntity<ApiResponseWrapper<?>> getAllLjubimci() {
        List<Ljubimac> ljubimci = ljubimacService.getAllLjubimci();

        if (ljubimci.isEmpty()) {
            throw new EntityNotFoundException("Kolekcija je prazna");
        }

        ljubimci.sort(Comparator.comparing(Ljubimac::getId));
        ApiResponseWrapper<List<Ljubimac>> apiResponseWrapper = new ApiResponseWrapper<>("OK", "Dohvaćena je cijela kolekcija ljubimaca", ljubimci);

        return ResponseEntity.ok(apiResponseWrapper);
    }



    //  Get za dohvacanje pojedinačnog resursa iz kolekcije
    @GetMapping("/id/{idLjubimca}")
    public ResponseEntity<ApiResponseWrapper<?>> getLjubimacById(@PathVariable Long idLjubimca) {
        Ljubimac ljubimac = ljubimacService.getLjubimacById(idLjubimca);

        if (ljubimac == null) {
            throw new EntityNotFoundException("Ljubimac s ID-om " + idLjubimca + " nije pronađen");
        }

        LjubimacResponse ljubimacResponse = LjubimacResponse.fromLjubimac(ljubimac);
        ApiResponseWrapper<LjubimacResponse> apiResponseWrapper = new ApiResponseWrapper<>("OK", "Dohvaćen ljubimac s ID-om " + idLjubimca, ljubimacResponse);

        return ResponseEntity.ok(apiResponseWrapper);
    }



    //  1. Get po vlastitom izboru dohvaca sve ljubimce po adresi
    @GetMapping("/adresa/{adresaLjubimca}")
    public ResponseEntity<ApiResponseWrapper<?>> getLjubimciByAdresa(@PathVariable String adresaLjubimca) {
        List<Ljubimac> ljubimci = ljubimacService.getLjubimciByAdresa(adresaLjubimca);

        if (ljubimci.isEmpty()) {
            throw new EntityNotFoundException("Nema ljubimaca na adresi " + adresaLjubimca);
        }

        List<LjubimacResponse> ljubimacResponses = ljubimci.stream()
                .map(LjubimacResponse::fromLjubimac)
                .sorted(Comparator.comparing(LjubimacResponse::getIdLjubimac))
                .collect(Collectors.toList());

        ApiResponseWrapper<List<LjubimacResponse>> apiResponseWrapper = new ApiResponseWrapper<>("OK", "Dohvaćeni ljubimci na adresi " + adresaLjubimca, ljubimacResponses);
        return ResponseEntity.ok(apiResponseWrapper);
    }




    //  2. Get po vlastitom izboru dohvaca sve ljubimce po imenu
    @GetMapping("/ime/{imeLjubimca}")
    public ResponseEntity<ApiResponseWrapper<?>> getLjubimciByIme(@PathVariable String imeLjubimca) {
        List<Ljubimac> ljubimci = ljubimacService.getLjubimciByIme(imeLjubimca);

        if (ljubimci.isEmpty()) {
            throw new EntityNotFoundException("Nema ljubimaca s imenom " + imeLjubimca);
        }

        List<LjubimacResponse> ljubimacResponses = ljubimci.stream()
                .map(LjubimacResponse::fromLjubimac)
                .sorted(Comparator.comparing(LjubimacResponse::getIdLjubimac))
                .collect(Collectors.toList());

        ApiResponseWrapper<List<LjubimacResponse>> apiResponseWrapper = new ApiResponseWrapper<>("OK", "Dohvaćeni ljubimci sa imenom " + imeLjubimca, ljubimacResponses);

        return ResponseEntity.ok(apiResponseWrapper);
    }




    //  3. Get po vlastitom izboru dohvaca sve ljubimce po vrsti
    @GetMapping("/vrsta/{vrstaLjubimca}")
    public ResponseEntity<ApiResponseWrapper<?>> getLjubimciByVrsta(@PathVariable String vrstaLjubimca) {
        List<Ljubimac> ljubimci = ljubimacService.getLjubimciByVrsta(vrstaLjubimca);

        if (ljubimci.isEmpty()) {
            throw new EntityNotFoundException("Nema ljubimaca sa vrstom " + vrstaLjubimca);
        }

        List<LjubimacResponse> ljubimacResponses = ljubimci.stream()
                .map(LjubimacResponse::fromLjubimac)
                .sorted(Comparator.comparing(LjubimacResponse::getIdLjubimac))
                .collect(Collectors.toList());

        ApiResponseWrapper<List<LjubimacResponse>> apiResponseWrapper = new ApiResponseWrapper<>("OK", "Dohvaćeni ljubimci sa vrstom " + vrstaLjubimca, ljubimacResponses);

        return ResponseEntity.ok(apiResponseWrapper);
    }




    //  Post za ubacivanje pojedinačnog resursa u kolekciju
    @PostMapping
    public ResponseEntity<ApiResponseWrapper<?>> createLjubimacWithVlasnik(@RequestBody LjubimacRequest ljubimacRequest) throws Exception {
        Vlasnik vlasnik = vlasnikService.createVlasnik(ljubimacRequest.toVlasnik());

        if (vlasnik == null) {
            throw new ElementCreationException("Nije moguće kreirati vlasnika");
        }

        Ljubimac ljubimac = ljubimacService.createLjubimac(ljubimacRequest.toLjubimac(), vlasnik);

        if (ljubimac == null) {
            throw new ElementCreationException("Nije moguće kreirati ljubimca");
        }

        ApiResponseWrapper<Ljubimac> apiResponseWrapper = new ApiResponseWrapper<>("OK", "Uspješno stvoren ljubimac s vlasnikom", ljubimac);

        return ResponseEntity.ok(apiResponseWrapper);
    }




    //  Put za će se ažuriranje elemenata resursa
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseWrapper<?>> updateLjubimacWithVlasnik(@PathVariable Long id, @RequestBody LjubimacRequest ljubimacRequest) {
        try {
            Ljubimac updatedLjubimac = ljubimacService.updateLjubimac(id, ljubimacRequest);
            if (updatedLjubimac == null) {
                throw new EntityNotFoundException("Ljubimca s ID-om " + id + " nije moguće ažurirati");
            }

            ApiResponseWrapper<Ljubimac> apiResponseWrapper = new ApiResponseWrapper<>("OK", "Uspješno ažuriran ljubimac s ID-om " + id, updatedLjubimac);

            return ResponseEntity.ok(apiResponseWrapper);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(ex.getMessage());
        } catch (Exception ex) {
            throw new ElementCreationException("Nije moguce ažurirati podatake");
        }
    }


    //  Delete za brisanje pojedinog resursa iz kolekcije temeljem
    //  jedinstvenog identifikatora resursa
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseWrapper<?>> deleteLjubimac(@PathVariable Long id) {
        boolean ljubimacDeleted = ljubimacService.deleteLjubimac(id);

        if (!ljubimacDeleted) {
            throw new EntityNotFoundException("Ljubimca s ID-om " + id + " nije moguće izbrisati");
        }

        ApiResponseWrapper<Void> apiResponseWrapper = new ApiResponseWrapper<>("OK", "Uspješno izbrisan ljubimac s ID-om " + id, null);
        return ResponseEntity.ok(apiResponseWrapper);
    }
}