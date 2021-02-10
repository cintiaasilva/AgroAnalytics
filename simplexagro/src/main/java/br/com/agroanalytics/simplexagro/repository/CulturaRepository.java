package br.com.agroanalytics.simplexagro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.agroanalytics.simplexagro.domain.Cultura;

@Repository
public interface CulturaRepository extends JpaRepository<Cultura, Long> {

	@Query("select u.cicloDiasMaturacao from Cultura as u where u.id = :id")
	public int buscarMaturacaoPorId(@Param("id") Long id);
	
}
