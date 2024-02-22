package com.entra21.gfarm.service;

import com.entra21.gfarm.dto.EquipamentoDTO;
import com.entra21.gfarm.model.Equipamento;
import com.entra21.gfarm.model.Fazenda;
import com.entra21.gfarm.repository.EquipamentoRepository;
import com.entra21.gfarm.repository.FazendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipamentoService {

  private final EquipamentoRepository equipamentoRepository;
  private final FazendaRepository fazendaRepository;

  @Autowired
  public EquipamentoService(EquipamentoRepository equipamentoRepository, FazendaRepository fazendaRepository) {
    this.equipamentoRepository = equipamentoRepository;
    this.fazendaRepository = fazendaRepository;
  }

  @Transactional(readOnly = true)
  public List<EquipamentoDTO> getAllEquipamentos() {
    List<Equipamento> equipamentos = equipamentoRepository.findAll();
    return equipamentos.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public EquipamentoDTO getEquipamentoById(Long id) {
    Optional<Equipamento> optionalEquipamento = equipamentoRepository.findById(id);
    return optionalEquipamento.map(this::convertToDTO).orElse(null);
  }

  @Transactional
  public EquipamentoDTO addEquipamento(EquipamentoDTO equipamentoDTO) {
    Equipamento equipamento = convertToEntity(equipamentoDTO);
    equipamento = equipamentoRepository.save(equipamento);
    return convertToDTO(equipamento);
  }

  @Transactional
  public EquipamentoDTO updateEquipamento(Long id, EquipamentoDTO equipamentoDTO) {
    Optional<Equipamento> optionalEquipamento = equipamentoRepository.findById(id);
    if (optionalEquipamento.isPresent()) {
      Equipamento equipamento = optionalEquipamento.get();
      equipamento.setNome(equipamentoDTO.getNome());
      equipamento.setDescricao(equipamentoDTO.getDescricao());
      equipamento.setDataDeCompra(equipamentoDTO.getDataDeCompra());
      // Atualiza a fazenda associada, se fornecida
      if (equipamentoDTO.getFazendaId() != null) {
        Optional<Fazenda> optionalFazenda = fazendaRepository.findById(equipamentoDTO.getFazendaId());
        optionalFazenda.ifPresent(equipamento::setFazenda);
      }
      equipamento = equipamentoRepository.save(equipamento);
      return convertToDTO(equipamento);
    } else {
      return null; // Retorna null se o equipamento não for encontrado
    }
  }

  @Transactional
  public boolean deleteEquipamento(Long id) {
    Optional<Equipamento> optionalEquipamento = equipamentoRepository.findById(id);
    if (optionalEquipamento.isPresent()) {
      equipamentoRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  private EquipamentoDTO convertToDTO(Equipamento equipamento) {
    EquipamentoDTO dto = new EquipamentoDTO();
    dto.setId(equipamento.getId());
    dto.setNome(equipamento.getNome());
    dto.setDescricao(equipamento.getDescricao());
    dto.setDataDeCompra(equipamento.getDataDeCompra());
    if (equipamento.getFazenda() != null) {
      dto.setFazendaId(equipamento.getFazenda().getId());
    }
    return dto;
  }

  private Equipamento convertToEntity(EquipamentoDTO dto) {
    Equipamento equipamento = new Equipamento();
    equipamento.setId(dto.getId());
    equipamento.setNome(dto.getNome());
    equipamento.setDescricao(dto.getDescricao());
    equipamento.setDataDeCompra(dto.getDataDeCompra());
    if (dto.getFazendaId() != null) {
      Optional<Fazenda> optionalFazenda = fazendaRepository.findById(dto.getFazendaId());
      optionalFazenda.ifPresent(equipamento::setFazenda);
    }
    return equipamento;
  }

}
