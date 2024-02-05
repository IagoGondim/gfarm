package com.entra21.gfarm.fazenda;

import com.entra21.gfarm.usuario.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FazendaDTO {

    private Long id;
    private String nome;
    private int areaTotal;
    private UsuarioDTO usuario;

    public static FazendaDTO fromEntity(Fazenda fazenda) {
        return new FazendaDTO(
                fazenda.getId(),
                fazenda.getNome(),
                fazenda.getAreaTotal(),
                UsuarioDTO.fromEntity(fazenda.getUsuario())

        );
    }
}
