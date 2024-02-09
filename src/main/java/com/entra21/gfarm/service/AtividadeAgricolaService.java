package com.entra21.gfarm.service;

import java.util.List;
import java.util.Optional;

import com.entra21.gfarm.dto.AtividadeAgricolaDTO;
import com.entra21.gfarm.model.AtividadeAgricola;
import com.entra21.gfarm.repository.AtividadeAgricolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtividadeAgricolaService {

    private final AtividadeAgricolaRepository atividadeAgricolaRepository;

    @Autowired
    public AtividadeAgricolaService(AtividadeAgricolaRepository atividadeAgricolaRepository) {
        this.atividadeAgricolaRepository = atividadeAgricolaRepository;
    }

    public List<AtividadeAgricola> getAllAtividadesAgricolas() {
        return atividadeAgricolaRepository.findAll();
    }

    public Optional<AtividadeAgricola> getAtividadeAgricolaById(int id) {
        return atividadeAgricolaRepository.findById((long) id);
    }

    public AtividadeAgricola createAtividadeAgricola(AtividadeAgricolaDTO atividadeAgricolaDTO) {
        AtividadeAgricola atividadeAgricola = new AtividadeAgricola();
        atividadeAgricola.setDescricao(atividadeAgricolaDTO.getDescricao());
        atividadeAgricola.setDataDaAtividade(atividadeAgricolaDTO.getDataDaAtividade());
        return atividadeAgricolaRepository.save(atividadeAgricola);
    }

    public AtividadeAgricola updateAtividadeAgricola(int id, AtividadeAgricolaDTO atividadeAgricolaDTO) {
        Optional<AtividadeAgricola> optionalAtividadeAgricola = atividadeAgricolaRepository.findById((long) id);
        if (optionalAtividadeAgricola.isPresent()) {
            AtividadeAgricola atividadeAgricola = optionalAtividadeAgricola.get();
            atividadeAgricola.setDescricao(atividadeAgricolaDTO.getDescricao());
            atividadeAgricola.setDataDaAtividade(atividadeAgricolaDTO.getDataDaAtividade());
            return atividadeAgricolaRepository.save(atividadeAgricola);
        } else {
            return null;
        }
    }

    public void deleteAtividadeAgricola(int id) {
        atividadeAgricolaRepository.deleteById((long) id);
    }
}