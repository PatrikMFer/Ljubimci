package com.ferovac.backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "igracka")
public class Igracka {
    @Id
    private Integer id;
    private String naziv;
    private String boja;
    @ManyToOne
    @JoinColumn(name = "ljubimacid")
    private Ljubimac ljubimac;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public Ljubimac getLjubimac() {
        return ljubimac;
    }

    public void setLjubimac(Ljubimac ljubimac) {
        this.ljubimac = ljubimac;
    }
}
