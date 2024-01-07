package com.ferovac.backend.Repository;


import com.ferovac.backend.Entity.Ljubimac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LjubimacRepository extends JpaRepository<Ljubimac, Long> {


    List<Ljubimac> findByAdresa(String adresa);

    List<Ljubimac> findByIme(String ime);

    List<Ljubimac> findByVrsta(String vrsta);
}