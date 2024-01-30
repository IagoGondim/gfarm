package com.entra21.gfarm.colheita;

import com.entra21.gfarm.cultivo.Cultivo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tb_colheita")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Colheita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dataColheita;

    @Column
    private int quantidadeColhida;

    @OneToOne
    @JoinColumn(name = "cultivo_id", referencedColumnName = "id")
    private Cultivo cultivo;

}
