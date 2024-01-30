package com.entra21.gfarm.lote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/lotes")
public class LoteController {

    @Autowired
    private LoteService loteService;

    @GetMapping
    public ResponseEntity<Set<LoteDTO>> listarLotes() {
        Set<LoteDTO> lotesDTO = loteService.listarLotesDTO();
        return ResponseEntity.ok(lotesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteDTO> obterLotePorId(@PathVariable Long id) {
        LoteDTO loteDTO = loteService.obterLoteDTOPorId(id);
        return ResponseEntity.ok(loteDTO);
    }

    @PostMapping
    public ResponseEntity<LoteDTO> criarLote(@RequestBody LoteDTO loteDTO) {
        LoteDTO novaLoteDTO = loteService.criarLoteDTO(loteDTO);
        return ResponseEntity.ok(novaLoteDTO);
    }

}
