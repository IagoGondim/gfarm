package com.entra21.gfarm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class AtividadeAgricolaDTO {

  private Long id;
  private String titulo;
  private String descricao;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date data;

  @JsonFormat(pattern = "HH:mm:ss")
  private Date hora;

  private Set<FuncionarioDTO> funcionarios = new HashSet<>();
}
