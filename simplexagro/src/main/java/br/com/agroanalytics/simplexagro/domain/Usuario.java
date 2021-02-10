package br.com.agroanalytics.simplexagro.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.NotFound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	@NotNull  @NotFound  
	private Long id;
	
	@JsonProperty
	@NotNull  @NotFound @NotEmpty 
	private String nome;
	
	@JsonProperty
	@NotNull  @NotFound @NotEmpty 
	private String cpf;
	
	@JsonProperty
	private String dataNascimento;
	
	@JsonProperty
	@Embedded
	private Telefone telefone;
	
	@JsonProperty
	@Embedded
	private Localizacao localizacao;
	
	@JsonProperty
	@NotNull  @NotFound 
	@Embedded
	private Login login;
	
	public Usuario() {
	
	}
	
	public Usuario(String nome, String cpf, String dataNascimento) {

		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Long getId() {
		return id;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	

	

	
}
