package com.entra21.gfarm.dto;

import com.entra21.gfarm.model.AtividadeAgricola;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {

  private int id;
  private String nome;
  private String cargo;
  private Timestamp dataContratacao;
  private AtividadeAgricola atividadeAgricola;


}
