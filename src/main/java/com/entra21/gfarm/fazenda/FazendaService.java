package com.entra21.gfarm.fazenda;

import com.entra21.gfarm.usuario.Usuario;
import com.entra21.gfarm.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FazendaService {

  private final FazendaRepository fazendaRepository;
  private final UsuarioService usuarioService;

  @Autowired
  public FazendaService(FazendaRepository fazendaRepository, UsuarioService usuarioService) {
    this.fazendaRepository = fazendaRepository;
    this.usuarioService = usuarioService;
  }

  @Transactional
  public FazendaDTO criarFazendaDTO(FazendaDTO fazendaDTO) {
    Usuario usuarioAutenticado = usuarioService.obterUsuarioAutenticado();
    if (usuarioAutenticado == null) {
      return null;
    }

    Fazenda fazenda = new Fazenda();
    fazenda.setNome(fazendaDTO.getNome());
    fazenda.setAreaTotal(fazendaDTO.getAreaTotal());
    fazenda.setUsuario(usuarioAutenticado);

    fazendaRepository.save(fazenda);

    return FazendaDTO.fromEntity(fazenda);
  }


}
