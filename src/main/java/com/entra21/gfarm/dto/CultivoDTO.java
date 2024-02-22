package com.entra21.gfarm.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CultivoDTO {

  private Long id;
  private String nome;
  private String descricao;
  private Date dataDePlantio;
  private Date dataColheitaPrevista;
  private Long loteId;


}