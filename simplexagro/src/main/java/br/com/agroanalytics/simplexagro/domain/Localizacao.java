package br.com.agroanalytics.simplexagro.domain;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class Localizacao {
	
	@JsonProperty
	private long numeroCasa;
	
	@JsonProperty
	private String rua;
	
	@JsonProperty
	private String bairro;
	
	@JsonProperty
	private String cidade;
	
	@JsonProperty
	private String estado;
	
	@JsonProperty
	private String pais;

	public long getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(long numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	
	
	

}
