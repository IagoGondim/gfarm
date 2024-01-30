package com.entra21.gfarm.colheita;

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

    public static com.entra21.gfarm.colheita.ColheitaDTO fromEntity(Colheita colheita) {
        return new com.entra21.gfarm.colheita.ColheitaDTO(colheita.getId(), colheita.getDataColheita(), colheita.getQuantidadeColhida());
    }
}
