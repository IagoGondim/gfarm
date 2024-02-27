package com.entra21.gfarm.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AnaliseSoloDTO {
  private Long id;
  private String nomeLaboratorio;
  private String profundidadeAmostragem;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date dataAmostragem;
  private double pH;
  private double materiaOrganica;
  private double nitrogenioTotal;
  private double fosforo;
  private double potassio;
  private double calcio;
  private double magnesio;
  private double enxofre;
  private double zinco;
  private double cobre;
  private double boro;
  private double manganes;
  private Long loteId;
}
