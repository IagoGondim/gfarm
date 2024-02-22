package com.entra21.gfarm.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
