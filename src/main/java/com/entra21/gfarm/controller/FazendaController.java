package com.entra21.gfarm.controller;

import com.entra21.gfarm.dto.FazendaDTO;
import com.entra21.gfarm.service.FazendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario/fazendas")
public class FazendaController {

  @Autowired
  private FazendaService fazendaService;
  @CrossOrigin(origins = "http://127.0.0.1:5500")
  @PostMapping
  public ResponseEntity<FazendaDTO> criarFazenda(@RequestBody FazendaDTO fazendaDTO) {
    FazendaDTO novaFazendaDTO = fazendaService.criarFazendaDTO(fazendaDTO);
    return ResponseEntity.ok().body(novaFazendaDTO);
  }
  @CrossOrigin(origins = "http://127.0.0.1:5500")
  @GetMapping
  public ResponseEntity<List<FazendaDTO>> listarFazendas() {
    List<FazendaDTO> fazendasDTO = fazendaService.listarFazendas();
    return ResponseEntity.ok().body(fazendasDTO);
  }
}
