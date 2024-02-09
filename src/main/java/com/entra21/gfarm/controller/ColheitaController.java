package com.entra21.gfarm.controller;

import com.entra21.gfarm.dto.ColheitaDTO;
import com.entra21.gfarm.service.ColheitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/colheitas")
public class ColheitaController {

    @Autowired
    private ColheitaService colheitaService;

    @GetMapping
    public ResponseEntity<Set<ColheitaDTO>> listarColheitas() {
        Set<ColheitaDTO> colheitasDTO = colheitaService.listarColheitasDTO();
        return ResponseEntity.ok(colheitasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColheitaDTO> obterColheitaPorId(@PathVariable Long id) {
        ColheitaDTO colheitaDTO = colheitaService.obterColheitaDTOPorId(id);
        return ResponseEntity.ok(colheitaDTO);
    }

    @PostMapping
    public ResponseEntity<ColheitaDTO> criarColheita(@RequestBody ColheitaDTO colheitaDTO) {
        ColheitaDTO novaColheitaDTO = colheitaService.criarColheitaDTO(colheitaDTO);
        return ResponseEntity.ok(novaColheitaDTO);
    }

}