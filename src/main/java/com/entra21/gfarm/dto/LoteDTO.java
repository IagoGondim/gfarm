package com.entra21.gfarm.dto;

import lombok.*;


@Data
public class LoteDTO {

  private Long id;
  private String nome;
  private int areaTotalLote;
  private String tipoDeSolo;
  private Long fazendaId;
}
