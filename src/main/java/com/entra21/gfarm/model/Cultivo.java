package com.entra21.gfarm.model;

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

    @Column
    private String descricao;

    @Temporal(TemporalType.DATE)
    private Date dataDePlantio;

    @Temporal(TemporalType.DATE)
    private Date dataColheitaPrevista;

    @OneToOne
    @JoinColumn(name = "lote_id", referencedColumnName = "id")
    private Lote lote;

}
