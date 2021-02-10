package br.com.agroanalytics.simplexagro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.agroanalytics.simplexagro.domain.Login;
import br.com.agroanalytics.simplexagro.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Modifying
	@Transactional
	@Query("update Usuario u set u.nome = :nome, u.cpf = :cpf, u.dataNascimento = :dataNascimento where u.id = :id")
	public void atualizarUsuarios(@Param("nome") String nome, @Param ("cpf") String cpf, @Param("dataNascimento") String dataNascimento, @Param("id")Long id);

	
	@Query("select u from Usuario u where u.login = :login")
	public Usuario buscarLogin(@Param("login") Login login);

}
