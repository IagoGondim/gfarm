package com.entra21.gfarm.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EquipamentoDTO {
  private Long id;
  private String nome;
  private String descricao;
  private Timestamp dataDeCompra;
  private Long fazendaId;


}
