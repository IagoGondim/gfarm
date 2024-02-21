package com.entra21.gfarm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_fazenda")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fazenda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String nome;

  @Column
  private int areaTotal;

  @ManyToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id")
  private Usuario usuario;

  @OneToOne(mappedBy = "fazenda")
  private Endereco endereco;

  @OneToMany(mappedBy = "fazenda", cascade = CascadeType.ALL)
  private Set<Funcionario> funcionarios;
}