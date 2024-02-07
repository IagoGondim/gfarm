package com.entra21.gfarm.fazenda;

import com.entra21.gfarm.endereco.Endereco;
import com.entra21.gfarm.endereco.EnderecoRepository;
import com.entra21.gfarm.usuario.Usuario;
import com.entra21.gfarm.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
}
