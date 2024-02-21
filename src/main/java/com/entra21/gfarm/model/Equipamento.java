package com.entra21.gfarm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_equipamento")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Equipamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private String nome;
  @Column
  private String descricao;
  @Column
  private java.sql.Timestamp dataDeCompra;


}
