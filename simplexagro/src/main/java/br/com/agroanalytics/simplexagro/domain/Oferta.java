package br.com.agroanalytics.simplexagro.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Oferta {

	@Id
	@GeneratedValue
	private Long id;
	
	@JsonProperty
	private Long idColheita;
	
	@JsonProperty
	private double valorOferta;
	
	@JsonProperty
	private long quantCaixasCompradas;

	public Long getIdColheita() {
		return idColheita;
	}

	public void setIdColheita(Long idColheita) {
		this.idColheita = idColheita;
	}

	public double getValorOferta() {
		return valorOferta;
	}

	public void setValorOferta(double valorOferta) {
		this.valorOferta = valorOferta;
	}

	public long getQuantCaixasCompradas() {
		return quantCaixasCompradas;
	}

	public void setQuantCaixasCompradas(long quantCaixasCompradas) {
		this.quantCaixasCompradas = quantCaixasCompradas;
	}

	public Long getId() {
		return id;
	}

}
