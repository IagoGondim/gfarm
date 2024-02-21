package com.entra21.gfarm.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class AtividadeAgricolaDTO {
  private Long id;
  private String titulo;
  private String descricao;
  private Timestamp dataDaAtividade;
}
