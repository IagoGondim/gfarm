package com.entra21.gfarm.dto;

import com.entra21.gfarm.model.Cultivo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CultivoDTO {

    private Long id;
    private String nome;
    private Date dataDePlantio;
    private Date dataColheitaPrevista;

    public static CultivoDTO fromEntity(Cultivo cultivo) {
        return new CultivoDTO(cultivo.getId(), cultivo.getNome(), cultivo.getDataDePlantio(), cultivo.getDataColheitaPrevista());
    }
}