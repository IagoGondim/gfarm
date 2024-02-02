package com.entra21.gFarm.agricola;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividades-agricolas")
public class AtividadeAgricolaController {

    private final AtividadeAgricolaService atividadeAgricolaService;

    @Autowired
    public AtividadeAgricolaController(AtividadeAgricolaService atividadeAgricolaService) {
        this.atividadeAgricolaService = atividadeAgricolaService;
    }

    @GetMapping
    public ResponseEntity<List<AtividadeAgricola>> getAllAtividadesAgricolas() {
        return ResponseEntity.ok(atividadeAgricolaService.getAllAtividadesAgricolas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeAgricola> getAtividadeAgricolaById(@PathVariable int id) {
        return atividadeAgricolaService.getAtividadeAgricolaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AtividadeAgricola> createAtividadeAgricola(@RequestBody AtividadeAgricolaDTO atividadeAgricolaDTO) {
        AtividadeAgricola atividadeAgricola = atividadeAgricolaService.createAtividadeAgricola(atividadeAgricolaDTO);
        return ResponseEntity.ok(atividadeAgricola);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeAgricola> updateAtividadeAgricola(@PathVariable int id, @RequestBody AtividadeAgricolaDTO atividadeAgricolaDTO) {
        AtividadeAgricola atividadeAgricola = atividadeAgricolaService.updateAtividadeAgricola(id, atividadeAgricolaDTO);
        return atividadeAgricola != null
                ? ResponseEntity.ok(atividadeAgricola)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtividadeAgricola(@PathVariable int id) {
        atividadeAgricolaService.deleteAtividadeAgricola(id);
        return ResponseEntity.noContent().build();
    }
}