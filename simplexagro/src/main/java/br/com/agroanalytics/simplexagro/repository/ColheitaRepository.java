package br.com.agroanalytics.simplexagro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.agroanalytics.simplexagro.domain.Colheita;
import br.com.agroanalytics.simplexagro.domain.Plantacao;

@Repository
public interface ColheitaRepository extends JpaRepository<Colheita, Long>{
	
	@Query("select u.caixas from Colheita as u where u.id = :id")
	public Long buscarCaixas(@Param("id") Long id);

	public Plantacao findByPlantacao(Plantacao plantacao);
	
	@Modifying
	@Transactional
	@Query("update Colheita t set t.caixas = :caixas where t.id = :id")
	public void mudarEstado(@Param("caixas") Long caixas, @Param("id")Long id);

}
