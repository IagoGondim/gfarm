package com.entra21.gfarm.service;

import com.entra21.gfarm.dto.LoteDTO;
import com.entra21.gfarm.model.Lote;
import com.entra21.gfarm.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoteService {

    @Autowired
    private LoteRepository loteRepository;

    public Set<LoteDTO> listarLotesDTO() {
        Set<Lote> lotes = new HashSet<>(loteRepository.findAll());
        return lotes.stream().map(LoteDTO::fromEntity).collect(Collectors.toSet());
    }

    public LoteDTO obterLoteDTOPorId(Long id) {
        Lote lote = loteRepository.findById(id).orElse(null);
        return (lote != null) ? LoteDTO.fromEntity(lote) : null;
    }

    public LoteDTO criarLoteDTO(LoteDTO loteDTO) {
        Lote lote = new Lote();
        lote.setNome(loteDTO.getNome());
        lote.setAreaTotal(loteDTO.getAreaTotal());
        lote.setTipoDeSolo(loteDTO.getTipoDeSolo());
        Lote novaLote = loteRepository.save(lote);
        return LoteDTO.fromEntity(novaLote);
    }

}
