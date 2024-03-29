package com.entra21.gfarm.dto;

import com.entra21.gfarm.model.Fazenda;
import com.entra21.gfarm.model.Usuario;
import com.entra21.gfarm.enuns.UsuarioRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Set;

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
    boolean isAuthenticated = authentication != null && authentication.isAuthenticated();

    Set<FazendaDTO> fazendasDTO = new HashSet<>();
    if (isAuthenticated) {
      for (Fazenda fazenda : usuario.getFazendas()) {
        FazendaDTO fazendaDTO = new FazendaDTO();
        fazendaDTO.setId(fazenda.getId());
        fazendaDTO.setNome(fazenda.getNome());
        fazendaDTO.setAreaTotal(fazenda.getAreaTotal());
        fazendasDTO.add(fazendaDTO);
      }
    }

    return new UsuarioDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getCpf(),
            usuario.getEmail(),
            fazendasDTO,
            usuario.getRole()
    );
  }
}