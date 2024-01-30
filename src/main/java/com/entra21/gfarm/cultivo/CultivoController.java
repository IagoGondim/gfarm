package com.entra21.gfarm.cultivo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/cultivos")
public class CultivoController {

    @Autowired
    private CultivoService cultivoService;

    @GetMapping
    public ResponseEntity<Set<CultivoDTO>> listarCultivos() {
        Set<CultivoDTO> cultivosDTO = cultivoService.listarCultivosDTO();
        return ResponseEntity.ok(cultivosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CultivoDTO> obterCultivoPorId(@PathVariable Long id) {
        CultivoDTO cultivoDTO = cultivoService.obterCultivoDTOPorId(id);
        return ResponseEntity.ok(cultivoDTO);
    }

    @PostMapping
    public ResponseEntity<CultivoDTO> criarCultivo(@RequestBody CultivoDTO cultivoDTO) {
        CultivoDTO novaCultivoDTO = cultivoService.criarCultivoDTO(cultivoDTO);
        return ResponseEntity.ok(novaCultivoDTO);
    }

}