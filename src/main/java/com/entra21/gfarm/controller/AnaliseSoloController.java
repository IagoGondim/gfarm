package com.entra21.gfarm.controller;

import com.entra21.gfarm.dto.AnaliseSoloDTO;
import com.entra21.gfarm.service.AnaliseSoloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario/analises-solo")
public class AnaliseSoloController {

  private final AnaliseSoloService analiseSoloService;

  public AnaliseSoloController(AnaliseSoloService analiseSoloService) {
    this.analiseSoloService = analiseSoloService;
  }

  @GetMapping
  public ResponseEntity<List<AnaliseSoloDTO>> getAllAnalisesSolo() {
    List<AnaliseSoloDTO> analisesSolo = analiseSoloService.getAllAnalisesSolo();
    return ResponseEntity.ok(analisesSolo);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AnaliseSoloDTO> getAnaliseSoloById(@PathVariable Long id) {
    AnaliseSoloDTO analiseSolo = analiseSoloService.getAnaliseSoloById(id);
    if (analiseSolo != null) {
      return ResponseEntity.ok(analiseSolo);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<AnaliseSoloDTO> addAnaliseSolo(@RequestBody AnaliseSoloDTO analiseSoloDTO) {
    AnaliseSoloDTO novaAnaliseSolo = analiseSoloService.addAnaliseSolo(analiseSoloDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(novaAnaliseSolo);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AnaliseSoloDTO> atualizarAnaliseSolo(@PathVariable Long id, @RequestBody AnaliseSoloDTO analiseSoloDTO) {
    AnaliseSoloDTO analiseSoloAtualizada = analiseSoloService.atualizarAnaliseSolo(id, analiseSoloDTO);
    if (analiseSoloAtualizada != null) {
      return ResponseEntity.ok(analiseSoloAtualizada);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAnaliseSolo(@PathVariable Long id) {
    boolean deleted = analiseSoloService.deleteAnaliseSolo(id);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}