package com.ferovac.backend.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Ljubimac")
public class Ljubimac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ime", nullable = false, length = 50)
    private String ime;

    @Column(name = "vrsta", nullable = false, length = 50)
    private String vrsta;

    @Column(name = "spol", nullable = false, length = 25)
    private String spol;

    @Column(name = "dob", nullable = false)
    private int dob;

    @Column(name = "boja", nullable = false, length = 50)
    private String boja;

    @Column(name = "prehrana", nullable = false, length = 25)
    private String prehrana;

    @Column(name = "adresa", nullable = false, length = 50)
    private String adresa;

    @Column(name = "veterinar", nullable = false, length = 50)
    private String veterinar;

    @ManyToOne
    @JoinColumn(name = "id_vlasnik", nullable = false)
    private Vlasnik vlasnik;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getDob() {
        return dob;
    }

    public void setDob(int dob) {
        this.dob = dob;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public String getPrehrana() {
        return prehrana;
    }

    public void setPrehrana(String prehrana) {
        this.prehrana = prehrana;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getVeterinar() {
        return veterinar;
    }

    public void setVeterinar(String veterinar) {
        this.veterinar = veterinar;
    }

    public Vlasnik getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(Vlasnik vlasnik) {
        this.vlasnik = vlasnik;
    }
}
