package com.entra21.gfarm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "tb_funcionario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Funcionario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private String nome;
  @Column
  private String cargo;
  @Column
  private int salario;
  @Column
  private Timestamp dataContratacao;

  @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
  private Set<Fazenda> fazendas;

  @OneToOne
  @JoinColumn(name = "atividade_agricola_id")
  private AtividadeAgricola atividadeAgricola;


}
