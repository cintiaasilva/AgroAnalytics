package br.com.agroanalytics.simplexagro.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Cultura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty
	private String nome; 
	
	@JsonProperty
	private String tipo;

	@JsonProperty
	private double precoUnitario;
	
	@JsonProperty
	private int cicloDiasMaturacao;
	
	public Cultura() {
		
	}

	public Cultura(String nome, String tipo, double precoUnitario, int cicloDiasMaturacao) {
		this.nome = nome;
		this.tipo = tipo;
		this.precoUnitario = precoUnitario;
		this.cicloDiasMaturacao = cicloDiasMaturacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getCicloDiasMaturacao() {
		return cicloDiasMaturacao;
	}

	public void setCicloDiasMaturacao(int cicloDiasMaturacao) {
		this.cicloDiasMaturacao = cicloDiasMaturacao;
	}

	public Long getId() {
		return id;
	}
	
	
	

}
