package com.entra21.gfarm.service;

import com.entra21.gfarm.dto.AnaliseSoloDTO;
import com.entra21.gfarm.model.AnaliseSolo;
import com.entra21.gfarm.model.Lote;
import com.entra21.gfarm.repository.AnaliseSoloRepository;
import com.entra21.gfarm.repository.LoteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnaliseSoloService {

  private final AnaliseSoloRepository analiseSoloRepository;
  private final LoteRepository loteRepository;

  @Autowired
  public AnaliseSoloService(AnaliseSoloRepository analiseSoloRepository, LoteRepository loteRepository) {
    this.analiseSoloRepository = analiseSoloRepository;
    this.loteRepository = loteRepository;
  }

  @Transactional(readOnly = true)
  public List<AnaliseSoloDTO> getAllAnalisesSolo() {
    List<AnaliseSolo> analisesSolo = analiseSoloRepository.findAll();
    return analisesSolo.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public AnaliseSoloDTO getAnaliseSoloById(Long id) {
    Optional<AnaliseSolo> optionalAnaliseSolo = analiseSoloRepository.findById(id);
    return optionalAnaliseSolo.map(this::convertToDTO).orElse(null);
  }

  @Transactional
  public AnaliseSoloDTO addAnaliseSolo(AnaliseSoloDTO analiseSoloDTO) {
    if (analiseSoloDTO.getId() != null) {
      throw new IllegalArgumentException("O ID da análise de solo deve ser nulo para uma nova análise de solo.");
    }
    AnaliseSolo novaAnaliseSolo = convertToEntity(analiseSoloDTO);
    if (analiseSoloDTO.getLoteId() != null) {
      Optional<Lote> loteOptional = loteRepository.findById(analiseSoloDTO.getLoteId());
      if (loteOptional.isPresent()) {
        novaAnaliseSolo.setLotes(new HashSet<>(Collections.singletonList(loteOptional.get())));
      } else {
        throw new EntityNotFoundException("Lote não encontrado com o ID fornecido: " + analiseSoloDTO.getLoteId());
      }
    }

    novaAnaliseSolo = analiseSoloRepository.save(novaAnaliseSolo);
    return convertToDTO(novaAnaliseSolo);
  }


  @Transactional
  public AnaliseSoloDTO atualizarAnaliseSolo(Long id, AnaliseSoloDTO analiseSoloDTO) {
    Optional<AnaliseSolo> optionalAnaliseSolo = analiseSoloRepository.findById(id);
    if (optionalAnaliseSolo.isPresent()) {
      AnaliseSolo analiseSolo = optionalAnaliseSolo.get();
      analiseSolo.setNome(analiseSoloDTO.getNome());

      Optional<Lote> loteOptional = loteRepository.findById(analiseSoloDTO.getLoteId());
      if (loteOptional.isPresent()) {
        Lote lote = loteOptional.get();
        analiseSolo.setLotes(new HashSet<>(Collections.singletonList(lote)));
      } else {
        throw new EntityNotFoundException("Lote não encontrado com o ID fornecido: " + analiseSoloDTO.getLoteId());
      }
      analiseSolo = analiseSoloRepository.save(analiseSolo);
      return convertToDTO(analiseSolo);
    }
    return null;
  }


  @Transactional
  public boolean deleteAnaliseSolo(Long id) {
    if (analiseSoloRepository.existsById(id)) {
      analiseSoloRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  private AnaliseSoloDTO convertToDTO(AnaliseSolo analiseSolo) {
    AnaliseSoloDTO dto = new AnaliseSoloDTO();
    dto.setId(analiseSolo.getId());
    dto.setNome(analiseSolo.getNome());
    if (!analiseSolo.getLotes().isEmpty()) {
      dto.setLoteId(analiseSolo.getLotes().iterator().next().getId());
    }

    return dto;
  }


  private AnaliseSolo convertToEntity(AnaliseSoloDTO dto) {
    AnaliseSolo analiseSolo = new AnaliseSolo();
    analiseSolo.setId(dto.getId());
    analiseSolo.setNome(dto.getNome());
    return analiseSolo;
  }
}




