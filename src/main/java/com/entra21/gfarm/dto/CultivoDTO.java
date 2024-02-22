package com.entra21.gfarm.dto;

import com.entra21.gfarm.model.Cultivo;
import lombok.Data;

import java.util.Date;

@Data
public class CultivoDTO {
  private Long id;
  private String nome;
  private String descricao;
  private Date dataDePlantio;
  private Date dataColheitaPrevista;
  private Date dataColheita;
  private double quantidadeColhida;
  private Long loteId;

  public static CultivoDTO fromEntity(Cultivo cultivo) {
    if (cultivo == null) {
      return null;
    }

    CultivoDTO dto = new CultivoDTO();
    dto.setId(cultivo.getId());
    dto.setNome(cultivo.getNome());
    dto.setDescricao(cultivo.getDescricao());
    dto.setDataDePlantio(cultivo.getDataDePlantio());
    dto.setDataColheitaPrevista(cultivo.getDataColheitaPrevista());
    dto.setDataColheita(cultivo.getDataColheita());
    dto.setQuantidadeColhida(cultivo.getQuantidadeColhida());
    if (cultivo.getLote() != null) {
      dto.setLoteId(cultivo.getLote().getId());
    }
    return dto;
  }

  public static Cultivo toEntity(CultivoDTO cultivoDTO) {
    if (cultivoDTO == null) {
      return null;
    }

    Cultivo cultivo = new Cultivo();
    cultivo.setId(cultivoDTO.getId());
    cultivo.setNome(cultivoDTO.getNome());
    cultivo.setDescricao(cultivoDTO.getDescricao());
    cultivo.setDataDePlantio(cultivoDTO.getDataDePlantio());
    cultivo.setDataColheitaPrevista(cultivoDTO.getDataColheitaPrevista());
    cultivo.setDataColheita(cultivoDTO.getDataColheita());
    cultivo.setQuantidadeColhida(cultivoDTO.getQuantidadeColhida());

    return cultivo;
  }
}
