package com.entra21.gfarm.service;

import com.entra21.gfarm.dto.LoteDTO;
import com.entra21.gfarm.model.Fazenda;
import com.entra21.gfarm.model.Lote;
import com.entra21.gfarm.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoteService {

  private final LoteRepository loteRepository;

  @Autowired
  public LoteService(LoteRepository loteRepository) {
    this.loteRepository = loteRepository;
  }

  @Transactional(readOnly = true)
  public List<LoteDTO> getAllLotes() {
    List<Lote> lotes = loteRepository.findAll();
    return lotes.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public LoteDTO getLoteById(Long id) {
    Optional<Lote> optionalLote = loteRepository.findById(id);
    return optionalLote.map(this::convertToDTO).orElse(null);
  }

  @Transactional
  public LoteDTO addLote(LoteDTO loteDTO) {
    Lote lote = convertToEntity(loteDTO);
    lote = loteRepository.save(lote);
    return convertToDTO(lote);
  }

  @Transactional
  public LoteDTO atualizarLote(Long id, LoteDTO loteDTO) {
    Optional<Lote> optionalLote = loteRepository.findById(id);
    if (optionalLote.isPresent()) {
      Lote lote = optionalLote.get();
      lote.setNome(loteDTO.getNome());
      lote.setAreaTotalLote(loteDTO.getAreaTotalLote());

      if (loteDTO.getFazendaId() != null) {
        Fazenda fazenda = new Fazenda();
        fazenda.setId(loteDTO.getFazendaId());
        lote.setFazenda(fazenda);
      } else {
        lote.setFazenda(null);
      }

      lote = loteRepository.save(lote);
      return convertToDTO(lote);
    } else {
      return null;
    }
  }

  @Transactional
  public boolean deleteLote(Long id) {
    Optional<Lote> optionalLote = loteRepository.findById(id);
    if (optionalLote.isPresent()) {
      loteRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  private LoteDTO convertToDTO(Lote lote) {
    LoteDTO dto = new LoteDTO();
    dto.setId(lote.getId());
    dto.setNome(lote.getNome());
    dto.setAreaTotalLote(lote.getAreaTotalLote());
    if (lote.getFazenda() != null) {
      dto.setFazendaId(lote.getFazenda().getId());
    }
    return dto;
  }

  private Lote convertToEntity(LoteDTO dto) {
    Lote lote = new Lote();
    lote.setId(dto.getId());
    lote.setNome(dto.getNome());
    lote.setAreaTotalLote(dto.getAreaTotalLote());
    if (dto.getFazendaId() != null) {
      Fazenda fazenda = new Fazenda();
      fazenda.setId(dto.getFazendaId());
      lote.setFazenda(fazenda);
    }
    return lote;
  }
}
