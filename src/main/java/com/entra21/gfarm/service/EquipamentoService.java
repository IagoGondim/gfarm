package com.entra21.gfarm.service;

import com.entra21.gfarm.dto.EquipamentoDTO;
import com.entra21.gfarm.model.Equipamento;
import com.entra21.gfarm.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    @Autowired
    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    public List<Equipamento> getAllEquipamentos() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> getEquipamentoById(int id) {
        return equipamentoRepository.findById((long) id);
    }

    public Equipamento createEquipamento(EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = new Equipamento();
        equipamento.setNome(equipamentoDTO.getNome());
        equipamento.setDescricao(equipamentoDTO.getDescricao());
        equipamento.setDataDeCompra(equipamentoDTO.getDataDeCompra());
        return equipamentoRepository.save(equipamento);
    }

    public Equipamento updateEquipamento(int id, EquipamentoDTO equipamentoDTO) {
        Optional<Equipamento> optionalEquipamento = equipamentoRepository.findById((long) id);
        if (optionalEquipamento.isPresent()) {
            Equipamento equipamento = optionalEquipamento.get();
            equipamento.setNome(equipamentoDTO.getNome());
            equipamento.setDescricao(equipamentoDTO.getDescricao());
            equipamento.setDataDeCompra(equipamentoDTO.getDataDeCompra());
            return equipamentoRepository.save(equipamento);
        } else {
            return null;
        }
    }

    public void deleteEquipamento(int id) {
        equipamentoRepository.deleteById((long) id);
    }
}
