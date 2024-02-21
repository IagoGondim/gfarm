package com.entra21.gfarm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_atividadeAgricola")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AtividadeAgricola {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String titulo;

  @Column
  private String descricao;

  @Column
  private Timestamp dataDaAtividade;

  @ManyToMany
  @JoinTable(
          name = "atividade_agricola_funcionario",
          joinColumns = @JoinColumn(name = "atividade_agricola_id"),
          inverseJoinColumns = @JoinColumn(name = "funcionario_id")
  )
  private Set<Funcionario> funcionarios = new HashSet<>();
}

