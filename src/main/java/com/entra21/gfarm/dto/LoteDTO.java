package com.entra21.gfarm.dto;

import com.entra21.gfarm.model.Lote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteDTO {

    private Long id;
    private String nome;
    private int areaTotal;
    private String tipoDeSolo;

    public static LoteDTO fromEntity(Lote lote) {
        return new LoteDTO(lote.getId(), lote.getNome(), lote.getAreaTotal(), lote.getTipoDeSolo());
    }
}
