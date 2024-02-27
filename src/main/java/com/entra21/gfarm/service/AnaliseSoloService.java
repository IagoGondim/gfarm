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

      analiseSolo.setNomeLaboratorio(analiseSoloDTO.getNomeLaboratorio());
      analiseSolo.setDataAmostragem(analiseSoloDTO.getDataAmostragem());
      analiseSolo.setProfundidadeAmostragem(analiseSoloDTO.getProfundidadeAmostragem());
      analiseSolo.setPH(analiseSoloDTO.getPH());
      analiseSolo.setMateriaOrganica(analiseSoloDTO.getMateriaOrganica());
      analiseSolo.setNitrogenioTotal(analiseSoloDTO.getNitrogenioTotal());
      analiseSolo.setFosforo(analiseSoloDTO.getFosforo());
      analiseSolo.setPotassio(analiseSoloDTO.getPotassio());
      analiseSolo.setCalcio(analiseSoloDTO.getCalcio());
      analiseSolo.setMagnesio(analiseSoloDTO.getMagnesio());
      analiseSolo.setEnxofre(analiseSoloDTO.getEnxofre());
      analiseSolo.setZinco(analiseSoloDTO.getZinco());
      analiseSolo.setCobre(analiseSoloDTO.getCobre());
      analiseSolo.setBoro(analiseSoloDTO.getBoro());
      analiseSolo.setManganes(analiseSoloDTO.getManganes());

      if (analiseSoloDTO.getLoteId() != null) {
        Optional<Lote> loteOptional = loteRepository.findById(analiseSoloDTO.getLoteId());
        if (loteOptional.isPresent()) {
          Lote lote = loteOptional.get();
          analiseSolo.setLotes(new HashSet<>(Collections.singletonList(lote)));
        } else {
          throw new EntityNotFoundException("Lote não encontrado com o ID fornecido: " + analiseSoloDTO.getLoteId());
        }
      } else {
        analiseSolo.setLotes(null);
      }
      analiseSolo = analiseSoloRepository.save(analiseSolo);
      return convertToDTO(analiseSolo);
    } else {
      throw new EntityNotFoundException("Análise de solo não encontrada com o ID fornecido: " + id);
    }
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
    dto.setNomeLaboratorio(analiseSolo.getNomeLaboratorio());
    dto.setDataAmostragem(analiseSolo.getDataAmostragem());
    dto.setProfundidadeAmostragem(analiseSolo.getProfundidadeAmostragem());
    dto.setPH(analiseSolo.getPH());
    dto.setMateriaOrganica(analiseSolo.getMateriaOrganica());
    dto.setNitrogenioTotal(analiseSolo.getNitrogenioTotal());
    dto.setFosforo(analiseSolo.getFosforo());
    dto.setPotassio(analiseSolo.getPotassio());
    dto.setCalcio(analiseSolo.getCalcio());
    dto.setMagnesio(analiseSolo.getMagnesio());
    dto.setEnxofre(analiseSolo.getEnxofre());
    dto.setZinco(analiseSolo.getZinco());
    dto.setCobre(analiseSolo.getCobre());
    dto.setBoro(analiseSolo.getBoro());
    dto.setManganes(analiseSolo.getManganes());
    if (!analiseSolo.getLotes().isEmpty()) {
      dto.setLoteId(analiseSolo.getLotes().iterator().next().getId());
    }

    return dto;
  }


  private AnaliseSolo convertToEntity(AnaliseSoloDTO dto) {
    AnaliseSolo analiseSolo = new AnaliseSolo();
    analiseSolo.setId(dto.getId());
    analiseSolo.setNomeLaboratorio(dto.getNomeLaboratorio());
    analiseSolo.setDataAmostragem(dto.getDataAmostragem());
    analiseSolo.setProfundidadeAmostragem(dto.getProfundidadeAmostragem());
    analiseSolo.setPH(dto.getPH());
    analiseSolo.setMateriaOrganica(dto.getMateriaOrganica());
    analiseSolo.setNitrogenioTotal(dto.getNitrogenioTotal());
    analiseSolo.setFosforo(dto.getFosforo());
    analiseSolo.setPotassio(dto.getPotassio());
    analiseSolo.setCalcio(dto.getCalcio());
    analiseSolo.setMagnesio(dto.getMagnesio());
    analiseSolo.setEnxofre(dto.getEnxofre());
    analiseSolo.setZinco(dto.getZinco());
    analiseSolo.setCobre(dto.getCobre());
    analiseSolo.setBoro(dto.getBoro());
    analiseSolo.setManganes(dto.getManganes());
    return analiseSolo;
  }
}




