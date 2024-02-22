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
  public FuncionarioDTO atualizarFuncionario(Long id, FuncionarioDTO funcionarioDTO) {
    Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
    if (funcionarioOptional.isPresent()) {
      Funcionario funcionario = funcionarioOptional.get();
      funcionario.setNome(funcionarioDTO.getNome());
      funcionario.setCargo(funcionarioDTO.getCargo());
      funcionario.setSalario(funcionarioDTO.getSalario());
      funcionario.setDataContratacao(funcionarioDTO.getDataContratacao());
      // Outros campos que podem precisar de atualização

      funcionario = funcionarioRepository.save(funcionario);
      return FuncionarioDTO.fromEntity(funcionario);
    }
    return null;
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
