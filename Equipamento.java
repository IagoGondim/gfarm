package com.entra21.gFarm.equipamento;

import java.util.Set;

import com.entra21.gFarm.agricola.AtividadeAgricola;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_equipamento")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Equipamento {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 @Column
	 private String nome;
	 @Column
	 private String descricao;
	 @Column
	 private java.sql.Timestamp dataDeCompra;

	  @OneToMany(mappedBy = "equipamento", cascade = CascadeType.ALL)
	  private Set<AtividadeAgricola> atividadesAgricolas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public java.sql.Timestamp getDataDeCompra() {
		return dataDeCompra;
	}

	public void setDataDeCompra(java.sql.Timestamp timestamp) {
		this.dataDeCompra = timestamp;
	}

	public Set<AtividadeAgricola> getAtividadesAgricolas() {
		return atividadesAgricolas;
	}

	public void setAtividadesAgricolas(Set<AtividadeAgricola> atividadesAgricolas) {
		this.atividadesAgricolas = atividadesAgricolas;
	}
	  
	  
}
