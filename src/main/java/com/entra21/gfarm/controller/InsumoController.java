package com.entra21.gfarm.controller;


import com.entra21.gfarm.dto.InsumoDTO;
import com.entra21.gfarm.service.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario/insumos")
public class InsumoController {

  private final InsumoService insumoService;

  @Autowired
  public InsumoController(InsumoService insumoService) {
    this.insumoService = insumoService;
  }

  @GetMapping
  public ResponseEntity<List<InsumoDTO>> getAllInsumos() {
    List<InsumoDTO> insumos = insumoService.getAllInsumos();
    return ResponseEntity.ok(insumos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<InsumoDTO> getInsumoById(@PathVariable Long id) {
    InsumoDTO insumo = insumoService.getInsumoById(id);
    if (insumo != null) {
      return ResponseEntity.ok(insumo);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<InsumoDTO> addInsumo(@RequestBody InsumoDTO insumoDTO) {
    InsumoDTO newInsumo = insumoService.addInsumo(insumoDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(newInsumo);
  }

  @PutMapping("/{id}")
  public ResponseEntity<InsumoDTO> atualizarInsumo(@PathVariable Long id, @RequestBody InsumoDTO insumoDTO) {
    InsumoDTO insumoAtualizado = insumoService.atualizarInsumo(id, insumoDTO);
    if (insumoAtualizado != null) {
      return ResponseEntity.ok(insumoAtualizado);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteInsumo(@PathVariable Long id) {
    boolean deleted = insumoService.deleteInsumo(id);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}