package com.ferovac.backend.Repository;

import com.ferovac.backend.Entity.Vlasnik;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VlasnikRepository extends JpaRepository<Vlasnik, Long> {

}
