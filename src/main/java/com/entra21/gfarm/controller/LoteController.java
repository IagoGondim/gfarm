package com.entra21.gfarm.controller;

import com.entra21.gfarm.dto.LoteDTO;
import com.entra21.gfarm.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario/lotes")
public class LoteController {

  private final LoteService loteService;

  @Autowired
  public LoteController(LoteService loteService) {
    this.loteService = loteService;
  }

  @GetMapping
  public ResponseEntity<List<LoteDTO>> getAllLotes() {
    List<LoteDTO> lotes = loteService.getAllLotes();
    return ResponseEntity.ok(lotes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<LoteDTO> getLoteById(@PathVariable Long id) {
    LoteDTO lote = loteService.getLoteById(id);
    if (lote != null) {
      return ResponseEntity.ok(lote);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<LoteDTO> addLote(@RequestBody LoteDTO loteDTO) {
    LoteDTO newLote = loteService.addLote(loteDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(newLote);
  }

  @PutMapping("/{id}")
  public ResponseEntity<LoteDTO> atualizarLote(@PathVariable Long id, @RequestBody LoteDTO loteDTO) {
    LoteDTO loteAtualizado = loteService.atualizarLote(id, loteDTO);
    if (loteAtualizado != null) {
      return ResponseEntity.ok(loteAtualizado);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteLote(@PathVariable Long id) {
    boolean deleted = loteService.deleteLote(id);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
