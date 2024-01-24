package com.entra21.gfarm.fazenda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FazendaDTO {

  private Long id;
  private String nome;
  private int areaTotal;

  public static FazendaDTO fromEntity(Fazenda fazenda) {
    return new FazendaDTO(fazenda.getId(), fazenda.getNome(), fazenda.getAreaTotal());
  }
}
