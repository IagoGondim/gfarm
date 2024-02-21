package com.entra21.gfarm.service;

import com.entra21.gfarm.dto.AtividadeAgricolaDTO;
import com.entra21.gfarm.model.AtividadeAgricola;
import com.entra21.gfarm.model.Funcionario;
import com.entra21.gfarm.repository.AtividadeAgricolaRepository;
import com.entra21.gfarm.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtividadeAgricolaService {

  private final AtividadeAgricolaRepository atividadeAgricolaRepository;

  private final FuncionarioRepository funcionarioRepository;

  public AtividadeAgricolaService(AtividadeAgricolaRepository atividadeAgricolaRepository, FuncionarioRepository funcionarioRepository) {
    this.atividadeAgricolaRepository = atividadeAgricolaRepository;
    this.funcionarioRepository = funcionarioRepository;
  }

  public List<AtividadeAgricolaDTO> getAllAtividadesAgricolas() {
    List<AtividadeAgricola> atividadesAgricolas = atividadeAgricolaRepository.findAll();
    return atividadesAgricolas.stream()
            .map(this::convertAtividadeAgricolaToDTO)
            .collect(Collectors.toList());
  }


  public AtividadeAgricola createAtividadeAgricola(AtividadeAgricolaDTO atividadeAgricolaDTO, List<Long> funcionarioIds) {
    AtividadeAgricola atividadeAgricola = convertAtividadeAgricolaToEntity(atividadeAgricolaDTO);
    if (funcionarioIds != null && !funcionarioIds.isEmpty()) {
      List<Funcionario> funcionarios = funcionarioRepository.findAllById(funcionarioIds);
      atividadeAgricola.setFuncionarios(new HashSet<>(funcionarios));
    }
    return atividadeAgricolaRepository.save(atividadeAgricola);
  }

  private AtividadeAgricolaDTO convertAtividadeAgricolaToDTO(AtividadeAgricola atividadeAgricola) {
    AtividadeAgricolaDTO atividadeAgricolaDTO = new AtividadeAgricolaDTO();
    atividadeAgricolaDTO.setId(atividadeAgricola.getId());
    atividadeAgricolaDTO.setTitulo(atividadeAgricola.getTitulo());
    atividadeAgricolaDTO.setDescricao(atividadeAgricola.getDescricao());
    atividadeAgricolaDTO.setData(atividadeAgricola.getData());
    atividadeAgricolaDTO.setHora(atividadeAgricola.getHora());
    return atividadeAgricolaDTO;
  }

  private AtividadeAgricola convertAtividadeAgricolaToEntity(AtividadeAgricolaDTO atividadeAgricolaDTO) {
    AtividadeAgricola atividadeAgricola = new AtividadeAgricola();
    atividadeAgricola.setId(atividadeAgricolaDTO.getId());
    atividadeAgricola.setTitulo(atividadeAgricolaDTO.getTitulo());
    atividadeAgricola.setDescricao(atividadeAgricolaDTO.getDescricao());
    atividadeAgricola.setData(atividadeAgricolaDTO.getData());
    atividadeAgricola.setHora(atividadeAgricolaDTO.getHora());
    return atividadeAgricola;
  }
}
