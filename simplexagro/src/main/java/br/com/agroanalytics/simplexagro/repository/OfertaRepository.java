package br.com.agroanalytics.simplexagro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agroanalytics.simplexagro.domain.Oferta;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {

}
