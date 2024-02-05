package com.entra21.gfarm.usuario;

import com.entra21.gfarm.fazenda.Fazenda;
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
    // Obtém a autenticação do contexto de segurança do Spring Security
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    // Verifica se a autenticação não é nula e se possui um principal
    if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
      return (Usuario) authentication.getPrincipal();
    }

    // Retorna null se não houver usuário autenticado
    return null;
  }
}
