package com.entra21.gfarm.service;

import com.entra21.gfarm.dto.CultivoDTO;
import com.entra21.gfarm.model.Cultivo;
import com.entra21.gfarm.model.Lote;
import com.entra21.gfarm.repository.CultivoRepository;
import com.entra21.gfarm.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CultivoService {

  private final CultivoRepository cultivoRepository;
  private final LoteRepository loteRepository;

  @Autowired
  public CultivoService(CultivoRepository cultivoRepository, LoteRepository loteRepository) {
    this.cultivoRepository = cultivoRepository;
    this.loteRepository = loteRepository;
  }

  @Transactional(readOnly = true)
  public List<CultivoDTO> getAllCultivos() {
    List<Cultivo> cultivos = cultivoRepository.findAll();
    return cultivos.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public CultivoDTO getCultivoById(Long id) {
    Optional<Cultivo> optionalCultivo = cultivoRepository.findById(id);
    return optionalCultivo.map(this::convertToDto).orElse(null);
  }

  @Transactional
  public CultivoDTO addCultivo(CultivoDTO cultivoDTO) {
    Cultivo cultivo = convertToEntity(cultivoDTO);
    cultivo = cultivoRepository.save(cultivo);
    return convertToDto(cultivo);
  }

  @Transactional
  public boolean deleteCultivo(Long id) {
    Optional<Cultivo> optionalCultivo = cultivoRepository.findById(id);
    if (optionalCultivo.isPresent()) {
      cultivoRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  private CultivoDTO convertToDto(Cultivo cultivo) {
    CultivoDTO dto = new CultivoDTO();
    dto.setId(cultivo.getId());
    dto.setNome(cultivo.getNome());
    dto.setDescricao(cultivo.getDescricao());
    dto.setDataDePlantio(cultivo.getDataDePlantio());
    dto.setDataColheitaPrevista(cultivo.getDataColheitaPrevista());
    if (cultivo.getLote() != null) {
      dto.setLoteId(cultivo.getLote().getId());
    }
    return dto;
  }

  private Cultivo convertToEntity(CultivoDTO dto) {
    Cultivo cultivo = new Cultivo();
    cultivo.setId(dto.getId());
    cultivo.setNome(dto.getNome());
    cultivo.setDescricao(dto.getDescricao());
    cultivo.setDataDePlantio(dto.getDataDePlantio());
    cultivo.setDataColheitaPrevista(dto.getDataColheitaPrevista());
    if (dto.getLoteId() != null) {
      Optional<Lote> optionalLote = loteRepository.findById(dto.getLoteId());
      optionalLote.ifPresent(cultivo::setLote);
    }
    return cultivo;
  }
}
