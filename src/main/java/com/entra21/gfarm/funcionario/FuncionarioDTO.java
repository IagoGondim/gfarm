package com.entra21.gfarm.funcionario;

import com.entra21.gfarm.atividadeAgricola.AtividadeAgricola;
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
