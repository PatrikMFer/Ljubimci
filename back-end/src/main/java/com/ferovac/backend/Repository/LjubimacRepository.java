package com.ferovac.backend.Repository;


import com.ferovac.backend.Entity.Ljubimac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LjubimacRepository extends JpaRepository<Ljubimac, Long> {


    List<Ljubimac> findByAdresa(String adresa);

    List<Ljubimac> findByIme(String ime);

    List<Ljubimac> findByVrsta(String vrsta);

    @Query("SELECT lj FROM Ljubimac lj WHERE " +
            "UPPER(lj.ime) LIKE UPPER(CONCAT('%', :searchText, '%')) OR " +
            "UPPER(lj.vrsta) LIKE UPPER(CONCAT('%', :searchText, '%')) OR " +
            "UPPER(lj.spol) LIKE UPPER(CONCAT('%', :searchText, '%')) OR " +
            "UPPER(CAST(lj.dob AS string)) LIKE UPPER(CONCAT('%', :searchText, '%')) OR " +
            "UPPER(lj.boja) LIKE UPPER(CONCAT('%', :searchText, '%')) OR " +
            "UPPER(lj.prehrana) LIKE UPPER(CONCAT('%', :searchText, '%')) OR " +
            "UPPER(lj.adresa) LIKE UPPER(CONCAT('%', :searchText, '%')) OR " +
            "UPPER(lj.veterinar) LIKE UPPER(CONCAT('%', :searchText, '%')) OR " +
            "UPPER(lj.vlasnik.ime) LIKE UPPER(CONCAT('%', :searchText, '%')) OR " +
            "UPPER(lj.vlasnik.prezime) LIKE UPPER(CONCAT('%', :searchText, '%'))")
    List<Ljubimac> findByWildCardSearch(@Param("searchText") String searchText);

    List<Ljubimac> findByImeContainingIgnoreCase(String ime);

    List<Ljubimac> findByVrstaContainingIgnoreCase(String vrsta);

    List<Ljubimac> findBySpolContainingIgnoreCase(String spol);

    List<Ljubimac> findByDob(int dob);

    List<Ljubimac> findByBojaContainingIgnoreCase(String boja);

    List<Ljubimac> findByPrehranaContainingIgnoreCase(String prehrana);

    List<Ljubimac> findByAdresaContainingIgnoreCase(String adresa);

    List<Ljubimac> findByVeterinarContainingIgnoreCase(String veterinar);

    List<Ljubimac> findByVlasnik_ImeContainingIgnoreCase(String imeVlasnika);

    List<Ljubimac> findByVlasnik_PrezimeContainingIgnoreCase(String prezimeVlasnika);
}