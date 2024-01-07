package com.ferovac.backend.dto;

import com.ferovac.backend.Entity.Ljubimac;
import com.ferovac.backend.Entity.Vlasnik;

import lombok.Data;

@Data
public class LjubimacRequest {

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


    public Ljubimac toLjubimac() {
        Ljubimac ljubimac = new Ljubimac();
        ljubimac.setIme(this.imeLjubimac);
        ljubimac.setVrsta(this.vrsta);
        ljubimac.setSpol(this.spol);
        ljubimac.setDob(this.dob);
        ljubimac.setBoja(this.boja);
        ljubimac.setPrehrana(this.prehrana);
        ljubimac.setAdresa(this.adresa);
        ljubimac.setVeterinar(this.veterinar);
        return ljubimac;
    }

    public Vlasnik toVlasnik() {
        Vlasnik vlasnik = new Vlasnik();
        vlasnik.setIme(this.imeVlasnika);
        vlasnik.setPrezime(this.prezimeVlasnika);
        return vlasnik;
    }
}


