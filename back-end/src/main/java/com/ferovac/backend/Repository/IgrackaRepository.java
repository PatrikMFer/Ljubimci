package com.ferovac.backend.Repository;

import com.ferovac.backend.Entity.Igracka;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IgrackaRepository extends JpaRepository<Igracka, Integer> {
}
