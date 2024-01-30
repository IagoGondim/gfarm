package com.entra21.gfarm.cultivo;

import com.entra21.gfarm.lote.Lote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tb_cultivo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cultivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Temporal(TemporalType.DATE)
    private Date dataDePlantio;

    @Temporal(TemporalType.DATE)
    private Date dataColheitaPrevista;

    @OneToOne
    @JoinColumn(name = "lote_id", referencedColumnName = "id")
    private Lote lote;

}
