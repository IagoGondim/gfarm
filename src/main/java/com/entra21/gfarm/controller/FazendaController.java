package com.entra21.gfarm.controller;

import com.entra21.gfarm.dto.FazendaDTO;
import com.entra21.gfarm.service.FazendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

  @CrossOrigin(origins = "http://127.0.0.1:5500")
  @GetMapping("/{id}")
  public ResponseEntity<FazendaDTO> getFazendaById(@PathVariable Long id) {
    FazendaDTO fazendaDTO = fazendaService.getFazendaById(id);
    if (fazendaDTO != null) {
      return new ResponseEntity<>(fazendaDTO, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @CrossOrigin(origins = "http://127.0.0.1:5500")
  @PutMapping("/{id}")
  public ResponseEntity<FazendaDTO> atualizarFazenda(@PathVariable Long id, @RequestBody FazendaDTO fazendaDTO) {
    FazendaDTO fazendaAtualizada = fazendaService.atualizarFazenda(id, fazendaDTO);
    if (fazendaAtualizada != null) {
      return ResponseEntity.ok().body(fazendaAtualizada);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @CrossOrigin(origins = "http://127.0.0.1:5500")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarFazenda(@PathVariable Long id) {
    boolean deletado = fazendaService.deletarFazenda(id);
    if (deletado) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
