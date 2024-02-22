package com.entra21.gfarm.service;

import com.entra21.gfarm.dto.InsumoDTO;
import com.entra21.gfarm.model.Fazenda;
import com.entra21.gfarm.model.Insumo;
import com.entra21.gfarm.repository.FazendaRepository;
import com.entra21.gfarm.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InsumoService {

  private final InsumoRepository insumoRepository;
  private final FazendaRepository fazendaRepository;

  @Autowired
  public InsumoService(InsumoRepository insumoRepository, FazendaRepository fazendaRepository) {
    this.insumoRepository = insumoRepository;
    this.fazendaRepository = fazendaRepository;
  }

  @Transactional(readOnly = true)
  public List<InsumoDTO> getAllInsumos() {
    List<Insumo> insumos = insumoRepository.findAll();
    return insumos.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public InsumoDTO getInsumoById(Long id) {
    Optional<Insumo> optionalInsumo = insumoRepository.findById(id);
    return optionalInsumo.map(this::convertToDTO).orElse(null);
  }

  @Transactional
  public InsumoDTO addInsumo(InsumoDTO insumoDTO) {
    Insumo insumo = convertToEntity(insumoDTO);
    insumo = insumoRepository.save(insumo);
    return convertToDTO(insumo);
  }

  @Transactional
  public InsumoDTO atualizarInsumo(Long id, InsumoDTO insumoDTO) {
    Optional<Insumo> optionalInsumo = insumoRepository.findById(id);
    if (optionalInsumo.isPresent()) {
      Insumo insumo = optionalInsumo.get();
      insumo.setNome(insumoDTO.getNome());
      insumo.setTipo(insumoDTO.getTipo());
      insumo.setQuantidadeDisponivel(insumoDTO.getQuantidadeDisponivel());
      insumo.setPrecoUnitario(insumoDTO.getPrecoUnitario());

      if (insumoDTO.getFazendaId() != null) {
        Fazenda fazenda = fazendaRepository.findById(insumoDTO.getFazendaId())
                .orElseThrow(() -> new RuntimeException("Fazenda com o ID " + insumoDTO.getFazendaId() + " não encontrada."));
        insumo.setFazenda(fazenda);
      } else {
        insumo.setFazenda(null);
      }

      insumo = insumoRepository.save(insumo);
      return convertToDTO(insumo);
    } else {
      return null;
    }
  }

  @Transactional
  public boolean deleteInsumo(Long id) {
    Optional<Insumo> optionalInsumo = insumoRepository.findById(id);
    if (optionalInsumo.isPresent()) {
      insumoRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  private InsumoDTO convertToDTO(Insumo insumo) {
    InsumoDTO dto = new InsumoDTO();
    dto.setId(insumo.getId());
    dto.setNome(insumo.getNome());
    dto.setTipo(insumo.getTipo());
    dto.setQuantidadeDisponivel(insumo.getQuantidadeDisponivel());
    dto.setPrecoUnitario(insumo.getPrecoUnitario());
    if (insumo.getFazenda() != null) {
      dto.setFazendaId(insumo.getFazenda().getId());
    }
    return dto;
  }

  public Insumo convertToEntity(InsumoDTO dto) {
    Insumo insumo = new Insumo();
    insumo.setId(dto.getId());
    insumo.setNome(dto.getNome());
    insumo.setTipo(dto.getTipo());
    insumo.setQuantidadeDisponivel(dto.getQuantidadeDisponivel());
    insumo.setPrecoUnitario(dto.getPrecoUnitario());

    if (dto.getFazendaId() != null) {
      Fazenda fazenda = fazendaRepository.findById(dto.getFazendaId()).orElse(null);
      if (fazenda != null) {
        insumo.setFazenda(fazenda);
      } else {
        throw new RuntimeException("Fazenda com o ID " + dto.getFazendaId() + " não encontrada.");
      }
    }
    return insumo;
  }
}
