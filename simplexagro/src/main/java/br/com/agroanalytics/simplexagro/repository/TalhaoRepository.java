package br.com.agroanalytics.simplexagro.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.agroanalytics.simplexagro.domain.Talhao;

@Repository
public interface TalhaoRepository extends JpaRepository<Talhao, Long> {

	@Modifying
	@Transactional
	@Query("update Talhao t set t.disponibilidade = :disponibilidade where t.id = :id")
	public void mudarEstado(@Param("disponibilidade") boolean disponibilidade, @Param("id")Long id);
	
	@Query("select u.disponibilidade from Talhao as u where u.id = :id")
	public boolean buscarDisponibilidade(@Param("id") Long id);

}

