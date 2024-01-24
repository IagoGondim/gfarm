package com.entra21.gfarm.fazenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FazendaService {

  @Autowired
  private FazendaRepository fazendaRepository;

  public Set<FazendaDTO> listarFazendasDTO() {
    Set<Fazenda> fazendas = new HashSet<>(fazendaRepository.findAll());
    return fazendas.stream().map(FazendaDTO::fromEntity).collect(Collectors.toSet());
  }

  public FazendaDTO obterFazendaDTOPorId(Long id) {
    Fazenda fazenda = fazendaRepository.findById(id).orElse(null);
    return (fazenda != null) ? FazendaDTO.fromEntity(fazenda) : null;
  }

  public FazendaDTO criarFazendaDTO(FazendaDTO fazendaDTO) {
    Fazenda fazenda = new Fazenda();
    fazenda.setNome(fazendaDTO.getNome());
    fazenda.setAreaTotal(fazendaDTO.getAreaTotal());
    Fazenda novaFazenda = fazendaRepository.save(fazenda);
    return FazendaDTO.fromEntity(novaFazenda);
  }

}
