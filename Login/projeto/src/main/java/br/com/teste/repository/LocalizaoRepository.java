package br.com.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.domain.Localizacao;

@Repository
public interface LocalizaoRepository extends JpaRepository<Localizacao, Long> {

}
