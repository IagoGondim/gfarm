package com.entra21.gfarm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_endereco")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Endereco {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String logradouro;
  private int numero;
  private String bairro;
  private String cidade;

  @OneToOne
  @JoinColumn(name = "fazenda_id", referencedColumnName = "id")
  private Fazenda fazenda;

}
