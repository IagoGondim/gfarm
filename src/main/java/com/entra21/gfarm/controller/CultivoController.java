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
    return new ResponseEntity<>(cultivos, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CultivoDTO> getCultivoById(@PathVariable Long id) {
    CultivoDTO cultivo = cultivoService.getCultivoById(id);
    if (cultivo != null) {
      return new ResponseEntity<>(cultivo, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<CultivoDTO> addCultivo(@RequestBody CultivoDTO cultivoDTO) {
    CultivoDTO novoCultivo = cultivoService.addCultivo(cultivoDTO);
    return ResponseEntity.ok().body(novoCultivo);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CultivoDTO> atualizarCultivo(@PathVariable Long id, @RequestBody CultivoDTO cultivoDTO) {
    CultivoDTO cultivoAtualizado = cultivoService.atualizarCultivo(id, cultivoDTO);
    if (cultivoAtualizado != null) {
      return ResponseEntity.ok().body(cultivoAtualizado);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCultivo(@PathVariable Long id) {
    boolean deleted = cultivoService.deleteCultivo(id);
    if (deleted) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
