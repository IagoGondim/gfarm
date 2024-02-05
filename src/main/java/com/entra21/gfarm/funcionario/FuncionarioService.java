package com.entra21.gfarm.funcionario;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service 
public class FuncionarioService {
	
	private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Optional<FuncionarioDTO> findById(int id) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById((long) id);
        return optionalFuncionario.map(FuncionarioService::convertToDTO);
    }

    public List<FuncionarioDTO> findAll() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(FuncionarioService::convertToDTO)
                .collect(Collectors.toList());
    }

    public void save(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = convertToEntity(funcionarioDTO);
        funcionarioRepository.save(funcionario);
    }

    public void deleteById(int id) {
        funcionarioRepository.deleteById((long) id);
    }

    private static FuncionarioDTO convertToDTO(Funcionario funcionario) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(funcionario.getId());
        funcionarioDTO.setNome(funcionario.getNome());
        funcionarioDTO.setCargo(funcionario.getCargo());
        funcionarioDTO.setDataContratacao(funcionario.getDataContratacao());
        return funcionarioDTO;
    }

    private Funcionario convertToEntity(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario(0, null, null, null);
        funcionario.setId(funcionarioDTO.getId());
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCargo(funcionarioDTO.getCargo());
        funcionario.setDataContratacao(funcionarioDTO.getDataContratacao());
        return funcionario;
    }
}