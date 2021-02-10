package br.com.agroanalytics.simplexagro.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Insumo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty
	private String nome;
	
	@JsonProperty
	private String tipo;
		
	@JsonProperty
	private int tempoAcao;
	
	@JsonProperty
	private double quantRecomendada;
	
	@JsonProperty
	private double precoUnitario;
	
	@JsonProperty
	private double volumeRecipiente;

	public Insumo() {}
	
	public Insumo(String nome, String tipo, int tempoAcao, double quantRecomendada, double precoUnitario,
			double volumeRecipiente) {
		
		this.nome = nome;
		this.tipo = tipo;
		this.tempoAcao = tempoAcao;
		this.quantRecomendada = quantRecomendada;
		this.precoUnitario = precoUnitario;
		this.volumeRecipiente = volumeRecipiente;
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

	public int getTempoAcao() {
		return tempoAcao;
	}

	public void setTempoAcao(int tempoAcao) {
		this.tempoAcao = tempoAcao;
	}

	public double getQuantRecomendada() {
		return quantRecomendada;
	}

	public void setQuantRecomendada(double quantRecomendada) {
		this.quantRecomendada = quantRecomendada;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public double getVolumeRecipiente() {
		return volumeRecipiente;
	}

	public void setVolumeRecipiente(double volumeRecipiente) {
		this.volumeRecipiente = volumeRecipiente;
	}

	public Long getId() {
		return id;
	}
	
	
	

}
