package com.ferovac.backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ljubimac")
public class Ljubimac {
    @Id
    private Integer id;
    private String ime;
    private String vrsta;
    private String spol;
    private Integer starost;
    @Column(name = "zivotnivijek")
    private Integer zivotniVijek;
    private String boja;
    @Column(name = "imevlasnika")
    private String imeVlasnika;
    private String prehrana;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public String getSpol() {
        return spol;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }

    public Integer getStarost() {
        return starost;
    }

    public void setStarost(Integer starost) {
        this.starost = starost;
    }

    public Integer getZivotniVijek() {
        return zivotniVijek;
    }

    public void setZivotniVijek(Integer zivotniVijek) {
        this.zivotniVijek = zivotniVijek;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public String getImeVlasnika() {
        return imeVlasnika;
    }

    public void setImeVlasnika(String imeVlasnika) {
        this.imeVlasnika = imeVlasnika;
    }

    public String getPrehrana() {
        return prehrana;
    }

    public void setPrehrana(String prehrana) {
        this.prehrana = prehrana;
    }
}
