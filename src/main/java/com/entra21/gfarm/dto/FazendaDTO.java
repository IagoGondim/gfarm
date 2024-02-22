package com.entra21.gfarm.dto;

import com.entra21.gfarm.model.Fazenda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FazendaDTO {
  private Long id;
  private String nome;
  private int areaTotal;
  private UsuarioDTO usuario;
  private EnderecoDTO endereco;
  private Set<FuncionarioDTO> funcionarios;

  public static FazendaDTO fromEntity(Fazenda fazenda) {
    if (fazenda == null) {
      return null;
    }

    Set<FuncionarioDTO> funcionariosDTO = null;
    if (fazenda.getFuncionario() != null) {
      funcionariosDTO = fazenda.getFuncionario().stream()
              .map(FuncionarioDTO::fromEntity)
              .collect(Collectors.toSet());
    }

    return new FazendaDTO(
            fazenda.getId(),
            fazenda.getNome(),
            fazenda.getAreaTotal(),
            UsuarioDTO.fromEntity(fazenda.getUsuario()),
            EnderecoDTO.fromEntity(fazenda.getEndereco()),
            funcionariosDTO
    );
  }
}
