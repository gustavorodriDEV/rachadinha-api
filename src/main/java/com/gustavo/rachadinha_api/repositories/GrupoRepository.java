package com.gustavo.rachadinha_api.repositories;

import com.gustavo.rachadinha_api.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo,Long> {
}
