package com.entra21.gfarm.controller;

import com.entra21.gfarm.dto.EquipamentoDTO;
import com.entra21.gfarm.service.EquipamentoService;
import com.entra21.gfarm.model.Equipamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    @Autowired
    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> getAllEquipamentos() {
        return ResponseEntity.ok(equipamentoService.getAllEquipamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> getEquipamentoById(@PathVariable int id) {
        return equipamentoService.getEquipamentoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Equipamento> createEquipamento(@RequestBody EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = equipamentoService.createEquipamento(equipamentoDTO);
        return ResponseEntity.ok(equipamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> updateEquipamento(@PathVariable int id, @RequestBody EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = equipamentoService.updateEquipamento(id, equipamentoDTO);
        return equipamento != null
                ? ResponseEntity.ok(equipamento)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipamento(@PathVariable int id) {
        equipamentoService.deleteEquipamento(id);
        return ResponseEntity.noContent().build();
    }
}
