package com.entra21.gfarm.repository;

import com.entra21.gfarm.model.AtividadeAgricola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeAgricolaRepository extends JpaRepository<AtividadeAgricola, Long> {}