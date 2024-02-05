package com.entra21.gfarm.funcionario;

import java.sql.Timestamp;
import java.util.Set;

import com.entra21.gFarm.agricola.AtividadeAgricola;
import com.entra21.gFarm.fazenda.Fazenda;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_funcionario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Funcionario {
	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String nome;
	@Column
	private String cargo;
	@Column
	private int salario;
	@Column
	private Timestamp dataContratacao;
	    
	    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	    private Set<Fazenda> fazendas;
	    
	    @OneToOne
	    @JoinColumn(name = "atividade_agricola_id")
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

		public int getSalario() {
			return salario;
		}

		public void setSalario(int salario) {
			this.salario = salario;
		}

		public Timestamp getDataContratacao() {
			return dataContratacao;
		}

		public void setDataContratacao(Timestamp dataContratacao) {
			this.dataContratacao = dataContratacao;
		}

		public String getCargo() {
			return cargo;
		}

		 public AtividadeAgricola getAtividadeAgricola() {
			return atividadeAgricola;
		}

		public void setAtividadeAgricola(AtividadeAgricola atividadeAgricola) {
			this.atividadeAgricola = atividadeAgricola;
		}

		public Funcionario(int id, String nome, String cargo, Timestamp dataContratacao) {
		        this.id = id;
		        this.nome = nome;
		        this.cargo = cargo != null ? cargo : "";
		        this.dataContratacao = dataContratacao;
		    }

	    public void setCargo(String cargo) {
	        if (!cargo.equals("gerente") && !cargo.equals("supervisor") && !cargo.equals("operador")) {
	            throw new IllegalArgumentException("Cargo inválido");
	        }
	        this.cargo = cargo;
	    }
	}
