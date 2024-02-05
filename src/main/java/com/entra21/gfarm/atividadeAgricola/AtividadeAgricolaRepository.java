package com.entra21.gfarm.atividadeAgricola;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeAgricolaRepository extends JpaRepository<AtividadeAgricola, Long> {}