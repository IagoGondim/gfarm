package com.entra21.gfarm.controller;

import com.entra21.gfarm.dto.AtividadeAgricolaDTO;
import com.entra21.gfarm.model.AtividadeAgricola;
import com.entra21.gfarm.service.AtividadeAgricolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario/atividades-agricolas")
public class AtividadeAgricolaController {

  private final AtividadeAgricolaService atividadeAgricolaService;

  @Autowired
  public AtividadeAgricolaController(AtividadeAgricolaService atividadeAgricolaService) {
    this.atividadeAgricolaService = atividadeAgricolaService;
  }

  @GetMapping
  public ResponseEntity<List<AtividadeAgricolaDTO>> getAllAtividadesAgricolas() {
    return ResponseEntity.ok(atividadeAgricolaService.getAllAtividadesAgricolas());
  }

  @PostMapping
  public ResponseEntity<AtividadeAgricola> createAtividadeAgricola(
          @RequestBody AtividadeAgricolaDTO atividadeAgricolaDTO,
          @RequestParam(value = "funcionarioIds", required = false) List<Long> funcionarioIds
  ) {
    AtividadeAgricola atividadeSalva = atividadeAgricolaService.createAtividadeAgricola(atividadeAgricolaDTO, funcionarioIds);
    return ResponseEntity.ok().body(atividadeSalva);
  }





}
