package com.entra21.gfarm.model;

import java.sql.Timestamp;


import com.entra21.gfarm.model.Equipamento;
import com.entra21.gfarm.model.Funcionario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_atividadeAgricola")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AtividadeAgricola {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
    private String descricao;
	@Column
    private Timestamp dataDaAtividade;
    
    @OneToOne(mappedBy = "atividadeAgricola")
    private Funcionario funcionario;
    
    @ManyToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;
    

}
