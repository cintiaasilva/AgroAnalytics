package br.com.agroanalytics.simplexagro.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Colheita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty
	@OneToOne
	@JoinColumn(name = "id_plantacao")
	private Plantacao plantacao;
	
	@JsonProperty
	private Long caixas;
	
	@JsonProperty
	private String dataColheita;
	
	@JsonProperty
	private double precoColheita;
	
	public Colheita() {
		
	}
	
	public Colheita(Plantacao plantacao, Long caixas) {
		this.plantacao = plantacao;
		this.caixas = caixas;
	}

	public Plantacao getPlantacao() {
		return plantacao;
	}

	public void setPlantacao(Plantacao plantacao) {
		this.plantacao = plantacao;
	}

	public Long getCaixas() {
		return caixas;
	}

	public void setCaixas(Long caixas) {
		this.caixas = caixas;
	}

	public Long getId() {
		return id;
	}

	public String getDataColheita() {
		return dataColheita;
	}

	public void setDataColheita(String dataColheita) {
		this.dataColheita = dataColheita;
	}

	public double getPrecoColheita() {
		return precoColheita;
	}

	public void setPrecoColheita(double precoColheita) {
		this.precoColheita = precoColheita;
	}
	
}

