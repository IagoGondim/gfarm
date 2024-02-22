package com.entra21.gfarm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_insumo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Insumo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String nome;

  @Column
  private String tipo;

  @Column
  private Integer quantidadeDisponivel;

  @Column
  private Double precoUnitario;

  @ManyToOne
  @JoinColumn(name = "fazenda_id", referencedColumnName = "id")
  private Fazenda fazenda;
}
