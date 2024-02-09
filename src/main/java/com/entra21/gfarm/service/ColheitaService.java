package com.entra21.gfarm.service;

import com.entra21.gfarm.dto.ColheitaDTO;
import com.entra21.gfarm.model.Colheita;
import com.entra21.gfarm.repository.ColheitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ColheitaService {

    @Autowired
    private ColheitaRepository colheitaRepository;

    public Set<ColheitaDTO> listarColheitasDTO() {
        Set<Colheita> colheitas = new HashSet<>(colheitaRepository.findAll());
        return colheitas.stream().map(ColheitaDTO::fromEntity).collect(Collectors.toSet());
    }

    public ColheitaDTO obterColheitaDTOPorId(Long id) {
        Colheita colheita = colheitaRepository.findById(id).orElse(null);
        return (colheita != null) ? ColheitaDTO.fromEntity(colheita) : null;
    }

    public ColheitaDTO criarColheitaDTO(ColheitaDTO colheitaDTO) {
        Colheita colheita = new Colheita();
        colheita.setDataColheita(colheitaDTO.getDataColheita());
        colheita.setQuantidadeColhida(colheitaDTO.getQuantidadeColhida());
        Colheita novaColheita = colheitaRepository.save(colheita);
        return ColheitaDTO.fromEntity(novaColheita);
    }

}
