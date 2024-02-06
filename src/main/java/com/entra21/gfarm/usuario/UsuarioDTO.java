package com.entra21.gfarm.usuario;

import com.entra21.gfarm.fazenda.FazendaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

  private Long id;
  private String nome;
  private String cpf;
  private String email;
  private Set<FazendaDTO> fazendas;
  private UsuarioRole role;

  public static UsuarioDTO fromEntity(Usuario usuario) {
    if (usuario == null) {
      return null;
    }

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
      String username = authentication.getName();

      Set<FazendaDTO> fazendasDTO = usuario.getFazendas().stream()
              .map(FazendaDTO::fromEntity)
              .collect(Collectors.toSet());

      return new UsuarioDTO(
              usuario.getId(),
              usuario.getNome(),
              usuario.getCpf(),
              usuario.getEmail(),
              fazendasDTO,
              usuario.getRole()
      );
    }

    return new UsuarioDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getCpf(),
            usuario.getEmail(),
            null,
            usuario.getRole()
    );
  }
}