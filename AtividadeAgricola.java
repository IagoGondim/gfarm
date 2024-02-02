package com.entra21.gFarm.agricola;

import java.sql.Timestamp;

import com.entra21.gFarm.equipamento.Equipamento;
import com.entra21.gFarm.funcionario.Funcionario;

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
    
    public AtividadeAgricola() {
    }

    public AtividadeAgricola(int id, String descricao, Timestamp dataDaAtividade) {
        this.id = id;
        this.descricao = descricao;
        this.dataDaAtividade = dataDaAtividade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getDataDaAtividade() {
        return dataDaAtividade;
    }

    public void setDataDaAtividade(Timestamp dataDaAtividade) {
        this.dataDaAtividade = dataDaAtividade;
    }
}
