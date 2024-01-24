package com.entra21.gfarm.fazenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/fazendas")
public class FazendaController {

  @Autowired
  private FazendaService fazendaService;

  @GetMapping
  public ResponseEntity<Set<FazendaDTO>> listarFazendas() {
    Set<FazendaDTO> fazendasDTO = fazendaService.listarFazendasDTO();
    return ResponseEntity.ok(fazendasDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FazendaDTO> obterFazendaPorId(@PathVariable Long id) {
    FazendaDTO fazendaDTO = fazendaService.obterFazendaDTOPorId(id);
    return ResponseEntity.ok(fazendaDTO);
  }

  @PostMapping
  public ResponseEntity<FazendaDTO> criarFazenda(@RequestBody FazendaDTO fazendaDTO) {
    FazendaDTO novaFazendaDTO = fazendaService.criarFazendaDTO(fazendaDTO);
    return ResponseEntity.ok(novaFazendaDTO);
  }

}
