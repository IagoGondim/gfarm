package com.entra21.gfarm.service;

import com.entra21.gfarm.model.Usuario;
import com.entra21.gfarm.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

  public Usuario obterUsuarioAutenticado() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
      return (Usuario) authentication.getPrincipal();
    }

    return null;
  }
}
