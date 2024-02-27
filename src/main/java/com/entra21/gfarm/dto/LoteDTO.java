package com.entra21.gfarm.dto;

import com.entra21.gfarm.model.AnaliseSolo;
import lombok.*;


@Data
public class LoteDTO {
  private Long id;
  private String nome;
  private int areaTotalLote;
  private Long fazendaId;
  private CultivoDTO cultivo;
  private AnaliseSolo analiseSolo;
}
