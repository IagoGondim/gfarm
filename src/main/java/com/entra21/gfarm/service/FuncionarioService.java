package com.entra21.gfarm.service;

import com.entra21.gfarm.dto.FuncionarioDTO;
import com.entra21.gfarm.model.Funcionario;
import com.entra21.gfarm.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

  private final FuncionarioRepository funcionarioRepository;

  @Autowired
  public FuncionarioService(FuncionarioRepository funcionarioRepository) {
    this.funcionarioRepository = funcionarioRepository;
  }

  @Transactional(readOnly = true)
  public List<FuncionarioDTO> getAllFuncionarios() {
    List<Funcionario> funcionarios = funcionarioRepository.findAll();
    return funcionarios.stream()
            .map(FuncionarioDTO::fromEntity)
            .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public FuncionarioDTO getFuncionarioById(Long id) {
    Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
    return optionalFuncionario.map(FuncionarioDTO::fromEntity).orElse(null);
  }

  @Transactional
  public FuncionarioDTO addFuncionario(FuncionarioDTO funcionarioDTO) {
    Funcionario funcionario = FuncionarioDTO.toEntity(funcionarioDTO);
    funcionario = funcionarioRepository.save(funcionario);
    return FuncionarioDTO.fromEntity(funcionario);
  }

  @Transactional
  public boolean deleteFuncionario(Long id) {
    Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
    if (optionalFuncionario.isPresent()) {
      funcionarioRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}
