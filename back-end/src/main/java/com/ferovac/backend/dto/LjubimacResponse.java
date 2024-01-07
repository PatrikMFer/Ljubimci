package com.ferovac.backend.dto;

import com.ferovac.backend.Entity.Ljubimac;
import com.ferovac.backend.Entity.Vlasnik;
import lombok.Data;

@Data
public class LjubimacResponse {

    private Long idLjubimac;
    private String imeLjubimac;
    private String vrsta;
    private String spol;
    private int dob;
    private String boja;
    private String prehrana;
    private String adresa;
    private String veterinar;
    private String imeVlasnika;
    private String prezimeVlasnika;

    public LjubimacResponse() {

    }

    public LjubimacResponse(Ljubimac ljubimac, Vlasnik vlasnik) {
        this.idLjubimac = ljubimac.getId();
        this.imeLjubimac = ljubimac.getIme();
        this.vrsta = ljubimac.getVrsta();
        this.spol = ljubimac.getSpol();
        this.dob = ljubimac.getDob();
        this.boja = ljubimac.getBoja();
        this.prehrana = ljubimac.getPrehrana();
        this.adresa = ljubimac.getAdresa();
        this.veterinar = ljubimac.getVeterinar();
        this.imeVlasnika = vlasnik.getIme();
        this.prezimeVlasnika = vlasnik.getPrezime();
    }

    public static LjubimacResponse fromLjubimac(Ljubimac ljubimac) {
        LjubimacResponse response = new LjubimacResponse();
        response.setIdLjubimac(ljubimac.getId());
        response.setImeLjubimac(ljubimac.getIme());
        response.setVrsta(ljubimac.getVrsta());
        response.setSpol(ljubimac.getSpol());
        response.setDob(ljubimac.getDob());
        response.setBoja(ljubimac.getBoja());
        response.setPrehrana(ljubimac.getPrehrana());
        response.setAdresa(ljubimac.getAdresa());
        response.setVeterinar(ljubimac.getVeterinar());

        Vlasnik vlasnik = ljubimac.getVlasnik();
        if (vlasnik != null) {
            response.setImeVlasnika(vlasnik.getIme());
            response.setPrezimeVlasnika(vlasnik.getPrezime());
        }

        return response;
    }
}
