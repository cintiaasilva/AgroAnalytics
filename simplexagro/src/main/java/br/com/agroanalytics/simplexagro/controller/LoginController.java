package br.com.agroanalytics.simplexagro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agroanalytics.simplexagro.domain.Login;
import br.com.agroanalytics.simplexagro.domain.Usuario;
import br.com.agroanalytics.simplexagro.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity login(@RequestBody Login login) {

		Usuario a = usuarioRepository.buscarLogin(login);

		if (a == null) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senha inválidos.");
		
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body("OK");

	}

}
