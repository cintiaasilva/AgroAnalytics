package br.com.agroanalytics.simplexagro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.agroanalytics.simplexagro.domain.Insumo;

public interface InsumoRepository extends JpaRepository<Insumo, Long> {

	@Query("select a.tempoAcao from Insumo as a where a.id = :id")
	public int buscarTempoAcaoPorId(@Param("id")Long id);
	
	@Query("select a.volumeRecipiente from Insumo as a where a.id = :id")
	public double buscarVolumeRecipiente(@Param("id")Long id);
	
}
