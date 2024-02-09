package com.entra21.gfarm.dto;

import com.entra21.gfarm.model.Fazenda;
import com.entra21.gfarm.usuario.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  public static FazendaDTO fromEntity(Fazenda fazenda) {
    if (fazenda == null) {
      return null;
    }

    return new FazendaDTO(
            fazenda.getId(),
            fazenda.getNome(),
            fazenda.getAreaTotal(),
            UsuarioDTO.fromEntity(fazenda.getUsuario()),
            EnderecoDTO.fromEntity(fazenda.getEndereco())
    );
  }
}