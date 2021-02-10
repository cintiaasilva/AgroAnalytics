package br.com.agroanalytics.simplexagro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.agroanalytics.simplexagro.domain.Cultura;
import br.com.agroanalytics.simplexagro.domain.Insumo;
import br.com.agroanalytics.simplexagro.domain.Plantacao;
import br.com.agroanalytics.simplexagro.domain.Talhao;

public interface PlantacaoRepository extends JpaRepository<Plantacao, Long> {

	public List<Plantacao> findByCultura(Cultura cultura);
	
	public Plantacao findByTalhoes(Talhao talhao);
	
	public List<Plantacao> findByInsumos(Insumo insumo);
	
    @Query("select a.id from Plantacao as a where a.talhoes = :talhao")
	public Long buscarPlantacaoPeloTalhao(@Param("talhao") Talhao talhao);
    
    @Query("select sum(a.quantLitrosAplicados) from Plantacao as a")
    public Double buscarTodosInsumosAplicados();



}
