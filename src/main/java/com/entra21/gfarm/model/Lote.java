package com.entra21.gfarm.model;

import com.entra21.gfarm.model.Fazenda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_lote")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private int areaTotalLote;

    @Column
    private String tipoDeSolo;

    @ManyToOne
    @JoinColumn(name = "fazenda_id", referencedColumnName = "id")
    private Fazenda fazenda;

}