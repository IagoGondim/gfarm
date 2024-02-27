package com.entra21.gfarm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "tb_analiseSolo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnaliseSolo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String nome;
  @Column
  private String profundidadeAmostragem;

  @ManyToMany
  @JoinTable(
          name = "analiseSolo_lote",
          joinColumns = @JoinColumn(name = "analiseSolo_id"),
          inverseJoinColumns = @JoinColumn(name = "lote_id"))
  private Set<Lote> lotes = new HashSet<>();
}
