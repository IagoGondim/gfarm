package com.entra21.gfarm.endereco;

import com.entra21.gfarm.fazenda.Fazenda;
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

  @Column
  private String logradouro;

  @Column
  private int numero;

  @Column
  private String bairro;

  @Column
  private String cidade;

  @OneToOne(mappedBy = "endereco")
  private Fazenda fazenda;
}
