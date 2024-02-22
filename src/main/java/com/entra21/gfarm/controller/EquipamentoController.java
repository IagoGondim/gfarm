package com.entra21.gfarm.controller;

import com.entra21.gfarm.dto.EquipamentoDTO;
import com.entra21.gfarm.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario/equipamentos")
public class EquipamentoController {

  private final EquipamentoService equipamentoService;

  @Autowired
  public EquipamentoController(EquipamentoService equipamentoService) {
    this.equipamentoService = equipamentoService;
  }

  @GetMapping
  public ResponseEntity<List<EquipamentoDTO>> getAllEquipamentos() {
    List<EquipamentoDTO> equipamentos = equipamentoService.getAllEquipamentos();
    return ResponseEntity.ok(equipamentos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EquipamentoDTO> getEquipamentoById(@PathVariable Long id) {
    EquipamentoDTO equipamento = equipamentoService.getEquipamentoById(id);
    if (equipamento != null) {
      return ResponseEntity.ok(equipamento);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<EquipamentoDTO> addEquipamento(@RequestBody EquipamentoDTO equipamentoDTO) {
    EquipamentoDTO newEquipamento = equipamentoService.addEquipamento(equipamentoDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(newEquipamento);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EquipamentoDTO> updateEquipamento(@PathVariable Long id, @RequestBody EquipamentoDTO equipamentoDTO) {
    EquipamentoDTO updatedEquipamento = equipamentoService.updateEquipamento(id, equipamentoDTO);
    if (updatedEquipamento != null) {
      return ResponseEntity.ok(updatedEquipamento);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEquipamento(@PathVariable Long id) {
    boolean deleted = equipamentoService.deleteEquipamento(id);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}

