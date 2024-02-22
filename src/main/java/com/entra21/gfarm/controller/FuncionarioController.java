package com.entra21.gfarm.controller;

import com.entra21.gfarm.dto.FuncionarioDTO;
import com.entra21.gfarm.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario/funcionarios")
public class FuncionarioController {

  private final FuncionarioService funcionarioService;

  @Autowired
  public FuncionarioController(FuncionarioService funcionarioService) {
    this.funcionarioService = funcionarioService;
  }

  @GetMapping
  public ResponseEntity<List<FuncionarioDTO>> getAllFuncionarios() {
    List<FuncionarioDTO> funcionarios = funcionarioService.getAllFuncionarios();
    return new ResponseEntity<>(funcionarios, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FuncionarioDTO> getFuncionarioById(@PathVariable Long id) {
    FuncionarioDTO funcionario = funcionarioService.getFuncionarioById(id);
    if (funcionario != null) {
      return new ResponseEntity<>(funcionario, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @CrossOrigin(origins = "http://127.0.0.1:5500")
  @PostMapping
  public ResponseEntity<FuncionarioDTO> addFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
    FuncionarioDTO novoFuncionario = funcionarioService.addFuncionario(funcionarioDTO);
    return ResponseEntity.ok().body(novoFuncionario);
  }

  @PutMapping("/{id}")
  public ResponseEntity<FuncionarioDTO> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioDTO funcionarioDTO) {
    FuncionarioDTO funcionarioAtualizado = funcionarioService.atualizarFuncionario(id, funcionarioDTO);
    if (funcionarioAtualizado != null) {
      return ResponseEntity.ok().body(funcionarioAtualizado);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
    boolean deleted = funcionarioService.deleteFuncionario(id);
    if (deleted) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}

