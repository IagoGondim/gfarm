package com.entra21.gfarm.repository;

import com.entra21.gfarm.model.Colheita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColheitaRepository extends JpaRepository<Colheita, Long> {
}
