package br.com.agroanalytics.simplexagro.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class GraficoInsumos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty
	private String data; 
	
	@JsonProperty
	private double totalInsumosGastos;
	
	@JsonProperty
	private double quantLitrosAplicados;
	
	@JsonProperty
	private double quantInsumosTotal;
	
	public GraficoInsumos() {

	}

	public GraficoInsumos(String data, double totalInsumosGastos, double quantLitrosAplicados, double quantInsumosTotal) {
		this.data = data;
		this.totalInsumosGastos = totalInsumosGastos;
		this.quantLitrosAplicados = quantLitrosAplicados;
		this.quantInsumosTotal = quantInsumosTotal;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getTotalInsumosGastos() {
		return totalInsumosGastos;
	}

	public void setTotalInsumosGastos(double totalInsumosGastos) {
		this.totalInsumosGastos = totalInsumosGastos;
	}

	public double getQuantLitrosAplicados() {
		return quantLitrosAplicados;
	}

	public void setQuantLitrosAplicados(double quantLitrosAplicados) {
		this.quantLitrosAplicados = quantLitrosAplicados;
	}

	public double getQuantInsumosTotal() {
		return quantInsumosTotal;
	}

	public void setQuantInsumosTotal(double quantInsumosTotal) {
		this.quantInsumosTotal = quantInsumosTotal;
	}
}
