package com.entra21.gfarm.service;

import com.entra21.gfarm.dto.FazendaDTO;
import com.entra21.gfarm.model.Endereco;
import com.entra21.gfarm.model.Fazenda;
import com.entra21.gfarm.model.Usuario;
import com.entra21.gfarm.repository.EnderecoRepository;
import com.entra21.gfarm.repository.FazendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FazendaService {

  private final FazendaRepository fazendaRepository;
  private final EnderecoRepository enderecoRepository;
  private final UsuarioService usuarioService;

  @Autowired
  public FazendaService(FazendaRepository fazendaRepository, EnderecoRepository enderecoRepository, UsuarioService usuarioService) {
    this.fazendaRepository = fazendaRepository;
    this.enderecoRepository = enderecoRepository;
    this.usuarioService = usuarioService;
  }

  @Transactional
  public FazendaDTO criarFazendaDTO(FazendaDTO fazendaDTO) {
    Usuario usuarioAutenticado = usuarioService.obterUsuarioAutenticado();
    if (usuarioAutenticado == null) {
      return null;
    }

    Endereco endereco = new Endereco();
    endereco.setLogradouro(fazendaDTO.getEndereco().getLogradouro());
    endereco.setNumero(fazendaDTO.getEndereco().getNumero());
    endereco.setBairro(fazendaDTO.getEndereco().getBairro());
    endereco.setCidade(fazendaDTO.getEndereco().getCidade());

    enderecoRepository.save(endereco);

    Fazenda fazenda = new Fazenda();
    fazenda.setNome(fazendaDTO.getNome());
    fazenda.setAreaTotal(fazendaDTO.getAreaTotal());
    fazenda.setUsuario(usuarioAutenticado);
    fazenda.setEndereco(endereco);

    fazendaRepository.save(fazenda);

    return FazendaDTO.fromEntity(fazenda);
  }

  @Transactional(readOnly = true)
  public List<FazendaDTO> listarFazendas() {
    List<Fazenda> fazendas = fazendaRepository.findAll();
    return fazendas.stream()
            .map(FazendaDTO::fromEntity)
            .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public FazendaDTO getFazendaById(Long id) {
    Optional<Fazenda> fazendaOptional = fazendaRepository.findById(id);
    return fazendaOptional.map(FazendaDTO::fromEntity).orElse(null);
  }

  @Transactional
  public FazendaDTO atualizarFazenda(Long id, FazendaDTO fazendaDTO) {
    Optional<Fazenda> fazendaOptional = fazendaRepository.findById(id);
    if (fazendaOptional.isPresent()) {
      Fazenda fazenda = fazendaOptional.get();
      fazenda.setNome(fazendaDTO.getNome());
      fazenda.setAreaTotal(fazendaDTO.getAreaTotal());

      Endereco endereco = fazenda.getEndereco();
      endereco.setLogradouro(fazendaDTO.getEndereco().getLogradouro());
      endereco.setNumero(fazendaDTO.getEndereco().getNumero());
      endereco.setBairro(fazendaDTO.getEndereco().getBairro());
      endereco.setCidade(fazendaDTO.getEndereco().getCidade());

      fazendaRepository.save(fazenda);
      return FazendaDTO.fromEntity(fazenda);
    }
    return null;
  }

  @Transactional
  public boolean deletarFazenda(Long id) {
    Optional<Fazenda> fazendaOptional = fazendaRepository.findById(id);
    if (fazendaOptional.isPresent()) {
      fazendaRepository.deleteById(id);
      return true;
    }
    return false;
  }
}
