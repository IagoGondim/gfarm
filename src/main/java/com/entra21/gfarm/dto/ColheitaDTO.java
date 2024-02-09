package com.entra21.gfarm.dto;

import com.entra21.gfarm.model.Colheita;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColheitaDTO {

    private Long id;
    private Date dataColheita;
    private int quantidadeColhida;

    public static ColheitaDTO fromEntity(Colheita colheita) {
        return new ColheitaDTO(colheita.getId(), colheita.getDataColheita(), colheita.getQuantidadeColhida());
    }
}
