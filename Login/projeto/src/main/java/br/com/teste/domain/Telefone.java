package br.com.teste.domain;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTelefone;
	
	@JsonProperty
	private String telefoneCelular;
	
	@JsonProperty
	private String telefoneFixo;

	public String getTelefoneCelular() {
		
		return telefoneCelular;
		
	}

	public void setTelefoneCelular(String telefoneCelular) {
		
		this.telefoneCelular = telefoneCelular;
		
	}

	public String getTelefoneFixo() {
		
		return telefoneFixo;
		
	}

	public void setTelefoneFixo(String telefoneFixo) {
		
		this.telefoneFixo = telefoneFixo;
		
	}
	
	

}
