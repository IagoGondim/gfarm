package com.entra21.gFarm.funcionario;

import java.sql.Timestamp;
import com.entra21.gFarm.agricola.AtividadeAgricola;

public class FuncionarioDTO {
	
	    private int id;
	    private String nome;
	    private String cargo;
	    private Timestamp dataContratacao;
	    private AtividadeAgricola atividadeAgricola;

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



		public String getCargo() {
			return cargo;
		}



		public void setCargo(String cargo) {
			this.cargo = cargo;
		}



		public Timestamp getDataContratacao() {
			return dataContratacao;
		}



		public void setDataContratacao(Timestamp dataContratacao) {
			this.dataContratacao = dataContratacao;
		}

		public AtividadeAgricola getAtividadeAgricola() {
			return atividadeAgricola;
		}

		public void setAtividadeAgricola(AtividadeAgricola atividadeAgricola) {
			this.atividadeAgricola = atividadeAgricola;
		}
}
