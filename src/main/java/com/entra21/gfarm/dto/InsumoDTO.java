package com.entra21.gfarm.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class InsumoDTO {

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

  private Long fazendaId;
}
