package com.entra21.gfarm.service;

import com.entra21.gfarm.dto.CultivoDTO;
import com.entra21.gfarm.model.Cultivo;
import com.entra21.gfarm.model.Lote;
import com.entra21.gfarm.repository.CultivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CultivoService {

  private final CultivoRepository cultivoRepository;

  @Autowired
  public CultivoService(CultivoRepository cultivoRepository) {
    this.cultivoRepository = cultivoRepository;
  }

  @Transactional(readOnly = true)
  public List<CultivoDTO> getAllCultivos() {
    List<Cultivo> cultivos = cultivoRepository.findAll();
    return cultivos.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public CultivoDTO getCultivoById(Long id) {
    Optional<Cultivo> optionalCultivo = cultivoRepository.findById(id);
    return optionalCultivo.map(this::convertToDTO).orElse(null);
  }

  @Transactional
  public CultivoDTO addCultivo(CultivoDTO cultivoDTO) {
    Cultivo cultivo = convertToEntity(cultivoDTO);
    cultivo = cultivoRepository.save(cultivo);
    return convertToDTO(cultivo);
  }

  @Transactional
  public CultivoDTO atualizarCultivo(Long id, CultivoDTO cultivoDTO) {
    Optional<Cultivo> optionalCultivo = cultivoRepository.findById(id);
    if (optionalCultivo.isPresent()) {
      Cultivo cultivo = optionalCultivo.get();
      cultivo.setNome(cultivoDTO.getNome());
      cultivo.setDescricao(cultivoDTO.getDescricao());
      cultivo.setDataDePlantio(cultivoDTO.getDataDePlantio());
      cultivo.setDataColheitaPrevista(cultivoDTO.getDataColheitaPrevista());
      cultivo.setDataColheita(cultivoDTO.getDataColheita());
      cultivo.setQuantidadeColhida(cultivoDTO.getQuantidadeColhida());
      if (cultivoDTO.getLoteId() != null) {
        Lote lote = new Lote();
        lote.setId(cultivoDTO.getLoteId());
        cultivo.setLote(lote);
      }
      cultivo = cultivoRepository.save(cultivo);
      return convertToDTO(cultivo);
    } else {
      return null;
    }
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

  private CultivoDTO convertToDTO(Cultivo cultivo) {
    CultivoDTO dto = new CultivoDTO();
    dto.setId(cultivo.getId());
    dto.setNome(cultivo.getNome());
    dto.setDescricao(cultivo.getDescricao());
    dto.setDataDePlantio(cultivo.getDataDePlantio());
    dto.setDataColheitaPrevista(cultivo.getDataColheitaPrevista());
    dto.setDataColheita(cultivo.getDataColheita());
    dto.setQuantidadeColhida(cultivo.getQuantidadeColhida());
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
    cultivo.setDataColheita(dto.getDataColheita());
    cultivo.setQuantidadeColhida(dto.getQuantidadeColhida());
    Lote lote = new Lote();
    lote.setId(dto.getLoteId());
    cultivo.setLote(lote);
    return cultivo;
  }
}
