package com.entra21.gfarm.dto;

import java.sql.Timestamp;

public class AtividadeAgricolaDTO {
	
	 private int id;
	 private String descricao;
	 private Timestamp dataDaAtividade;
	    
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
	    
		   public AtividadeAgricolaDTO(int id, String descricao, Timestamp dataDaAtividade) {
		        this.id = id;
		        this.descricao = descricao;
		        this.dataDaAtividade = dataDaAtividade;
		    }
}
