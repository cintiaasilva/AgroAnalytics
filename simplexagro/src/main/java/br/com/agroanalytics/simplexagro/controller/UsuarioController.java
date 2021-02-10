package br.com.agroanalytics.simplexagro.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agroanalytics.simplexagro.domain.Usuario;
import br.com.agroanalytics.simplexagro.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;


	@PostMapping
	@Transactional
	public ResponseEntity criarUsuario(@RequestBody @Valid Usuario usuario) {

		usuarioRepository.save(usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);

	}

	@GetMapping
	@Transactional
	public ResponseEntity<List<Usuario>> buscarUsuarios() {

		if (usuarioRepository.count() > 0) {
			List<Usuario> lista = usuarioRepository.findAll();
			return ResponseEntity.ok(lista);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity buscarUsuario(@PathVariable("id") Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isPresent()) {

			return ResponseEntity.ok(usuario);

		} else {

			return ResponseEntity.noContent().build();
		}

	}

	@PutMapping
	public ResponseEntity atualizarUsuario(@RequestBody Usuario usuario) {
		
		if(usuario.getId() == null) {
			
			return ResponseEntity.noContent().build();
			
			//criarUsuario(usuario);
		}
		usuarioRepository.save(usuario);
		
		return ResponseEntity.ok(usuario);
//		if (usuarioRepository.existsById(id)) {
//			
//			usuarioRepository.atualizarUsuarios(usuario.getNome(), usuario.getCpf(), usuario.getDataNascimento(), id);
//
//			return ResponseEntity.ok(usuario);
//
//		} else {
//
//			return ResponseEntity.notFound().build();
//
//		}

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluirUsuario(@PathVariable("id") Long id) {

		if (usuarioRepository.existsById(id)) {

			usuarioRepository.deleteById(id);

			return ResponseEntity.ok().build();
		} else {

			return ResponseEntity.notFound().build();
		}

	}

}
