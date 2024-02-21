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
@Table(name = "tb_funcionario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Funcionario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String nome;

  @Column
  private String cargo;

  @Column
  private int salario;

  @Column
  private Timestamp dataContratacao;

  @ManyToOne
  @JoinColumn(name = "fazenda_id", referencedColumnName = "id")
  private Fazenda fazenda;

  @ManyToMany(mappedBy = "funcionarios")
  private Set<AtividadeAgricola> atividadesAgricolas = new HashSet<>();
}
