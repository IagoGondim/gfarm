package com.entra21.gfarm.lote;

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

    public static com.entra21.gfarm.lote.LoteDTO fromEntity(Lote lote) {
        return new com.entra21.gfarm.lote.LoteDTO(lote.getId(), lote.getNome(), lote.getAreaTotal(), lote.getTipoDeSolo());
    }
}
