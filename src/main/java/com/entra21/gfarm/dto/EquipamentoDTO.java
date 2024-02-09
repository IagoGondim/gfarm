package com.entra21.gfarm.dto;

import java.sql.Timestamp;

public class EquipamentoDTO {

    private String nome;
    private String descricao;
    private Timestamp dataDeCompra;
    
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
	public Timestamp getDataDeCompra() {
		return dataDeCompra;
	}
	public void setDataDeCompra(Timestamp dataDeCompra) {
		this.dataDeCompra = dataDeCompra;
	}


}
