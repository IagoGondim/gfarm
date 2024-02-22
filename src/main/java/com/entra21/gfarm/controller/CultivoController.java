package com.entra21.gfarm.controller;

import com.entra21.gfarm.dto.CultivoDTO;
import com.entra21.gfarm.service.CultivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario/cultivos")
public class CultivoController {

  private final CultivoService cultivoService;

  @Autowired
  public CultivoController(CultivoService cultivoService) {
    this.cultivoService = cultivoService;
  }

  @GetMapping
  public ResponseEntity<List<CultivoDTO>> getAllCultivos() {
    List<CultivoDTO> cultivos = cultivoService.getAllCultivos();
    return ResponseEntity.ok(cultivos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CultivoDTO> getCultivoById(@PathVariable Long id) {
    CultivoDTO cultivo = cultivoService.getCultivoById(id);
    if (cultivo != null) {
      return ResponseEntity.ok(cultivo);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<CultivoDTO> addCultivo(@RequestBody CultivoDTO cultivoDTO) {
    CultivoDTO newCultivo = cultivoService.addCultivo(cultivoDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(newCultivo);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCultivo(@PathVariable Long id) {
    boolean deleted = cultivoService.deleteCultivo(id);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
