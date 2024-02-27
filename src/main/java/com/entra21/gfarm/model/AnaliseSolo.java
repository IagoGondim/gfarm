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
  private String nomeLaboratorio;
  @Column
  private String profundidadeAmostragem;
  @Column
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date dataAmostragem;
  @Column
  private double pH;
  @Column
  private double materiaOrganica;
  @Column
  private double nitrogenioTotal;
  @Column
  private double fosforo;
  @Column
  private double potassio;
  @Column
  private double calcio;
  @Column
  private double magnesio;
  @Column
  private double enxofre;
  @Column
  private double zinco;
  @Column
  private double cobre;
  @Column
  private double boro;
  @Column
  private double manganes;
  @ManyToMany
  @JoinTable(
          name = "analiseSolo_lote",
          joinColumns = @JoinColumn(name = "analiseSolo_id"),
          inverseJoinColumns = @JoinColumn(name = "lote_id"))
  private Set<Lote> lotes = new HashSet<>();
}
