package com.ferovac.backend.Controller;

import com.ferovac.backend.Entity.Ljubimac;
import com.ferovac.backend.Entity.Vlasnik;
import com.ferovac.backend.Service.VlasnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/vlasnici")
public class VlasnikController {

    private final VlasnikService vlasnikService;

    @Autowired
    public VlasnikController(VlasnikService vlasnikService) {
        this.vlasnikService = vlasnikService;
    }

    @GetMapping
    public ResponseEntity<List<Vlasnik>> getAllVlasnici() {
        List<Vlasnik> vlasnici = vlasnikService.getAllVlasnici();
        vlasnici.sort(Comparator.comparing(Vlasnik::getId));
        return ResponseEntity.ok(vlasnici);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vlasnik> getVlasnikById(@PathVariable Long id) {
        Vlasnik vlasnik = vlasnikService.getVlasnikById(id);
        return ResponseEntity.ok(vlasnik);
    }

}
