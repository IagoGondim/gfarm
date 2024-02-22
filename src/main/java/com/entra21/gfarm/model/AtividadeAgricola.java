package com.entra21.gfarm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date data;

  @Column
  @JsonFormat(pattern = "HH:mm:ss")
  private Date hora;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Adicionado CascadeType.ALL
  @JoinTable(
          name = "atividade_agricola_funcionario",
          joinColumns = @JoinColumn(name = "atividade_agricola_id"),
          inverseJoinColumns = @JoinColumn(name = "funcionario_id")
  )
  private Set<Funcionario> funcionarios = new HashSet<>();
}

