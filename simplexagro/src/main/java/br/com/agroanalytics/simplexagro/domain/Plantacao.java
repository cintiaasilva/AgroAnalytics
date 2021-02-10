package br.com.agroanalytics.simplexagro.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Plantacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty
	private String dataCriacao;

	@JsonProperty
	private String dataColheita;
	
	@JsonProperty
	private String dataConsumo;

	@JsonProperty
	@OneToOne
	@JoinColumn(name = "id_cultura")
	private Cultura cultura;

	@JsonProperty
	@OneToMany
	@JoinColumn(name = "id_talhao")
	private List<Talhao> talhoes;

	@JsonProperty
	@OneToMany
	@JoinColumn(name = "id_insumos")
	private List<Insumo> insumos;

	@JsonProperty
	private double quantLitrosAplicados;
	
	public Plantacao() {
	}
	
	public Plantacao(String dataCriacao, String dataColheita, String dataConsumo, Cultura cultura, List<Talhao> talhoes,
			List<Insumo> insumos) {
		this.dataCriacao = dataCriacao;
		this.dataColheita = dataColheita;
		this.dataConsumo = dataConsumo;
		this.cultura = cultura;
		this.talhoes = talhoes;
		this.insumos = insumos;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDataColheita() {
		return dataColheita;
	}

	public void setDataColheita(String dataColheita) {
		this.dataColheita = dataColheita;
	}

	public String getDataConsumo() {
		return dataConsumo;
	}

	public void setDataConsumo(String dataConsumo) {
		this.dataConsumo = dataConsumo;
	}

	public Cultura getCultura() {
		return cultura;
	}



	public void setCultura(Cultura cultura) {
		this.cultura = cultura;
	}



	public List<Talhao> getTalhoes() {
		return talhoes;
	}



	public void setTalhoes(List<Talhao> talhoes) {
		this.talhoes = talhoes;
	}



	public List<Insumo> getInsumos() {
		return insumos;
	}



	public void setInsumos(List<Insumo> insumos) {
		this.insumos = insumos;
	}



	public Long getId() {
		return id;
	}



	public void calcularDataColheita(int cicloDiasMaturacao) throws ParseException {

		String dataFinal;
		
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

		Date data = dataFormatada.parse(this.dataCriacao);
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(data);

		cal.add(Calendar.DAY_OF_MONTH, cicloDiasMaturacao);

		data = cal.getTime();
		
		dataFinal = dataFormatada.format(data);
		
		this.setDataColheita(dataFinal);
		
	}

	public void calcularDataConsumo(int tempoAcao) throws ParseException {
		
		String dataFinal;
		
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar cal = Calendar.getInstance();
		
		Date data = dataFormatada.parse(this.getDataCriacao());
		
		cal.setTime(data);
		
		cal.add(Calendar.DAY_OF_MONTH, tempoAcao);
		
		data = cal.getTime();
	
		dataFinal = dataFormatada.format(data);
		
		this.setDataConsumo(dataFinal);
	}

	public double getQuantLitrosAplicados() {
		return quantLitrosAplicados;
	}

	public void setQuantLitrosAplicados(double quantLitrosAplicados) {
		this.quantLitrosAplicados = quantLitrosAplicados;
	}
	
}
