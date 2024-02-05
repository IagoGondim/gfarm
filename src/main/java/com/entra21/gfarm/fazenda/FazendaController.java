package com.entra21.gfarm.fazenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("usuario/fazendas")
public class FazendaController {

  @Autowired
  private FazendaService fazendaService;


  @PostMapping
  public ResponseEntity<FazendaDTO> criarFazenda(@RequestBody FazendaDTO fazendaDTO) {
    FazendaDTO novaFazendaDTO = fazendaService.criarFazendaDTO(fazendaDTO);
    return ResponseEntity.ok().body(novaFazendaDTO);
  }

}
