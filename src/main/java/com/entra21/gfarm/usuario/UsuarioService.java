package com.entra21.gfarm.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  public Set<Usuario> listarUsuarios() {
    return new HashSet<>(usuarioRepository.findAll());
  }

  public Usuario obterUsuarioPorId(Long id) {
    return usuarioRepository.findById(id).orElse(null);
  }

  public Usuario criarUsuario(Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

}
