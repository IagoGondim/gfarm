package com.entra21.gFarm.agricola;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeAgricolaRepository extends JpaRepository<AtividadeAgricola, Long> {}