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

import br.com.agroanalytics.simplexagro.domain.Cultura;
import br.com.agroanalytics.simplexagro.domain.Plantacao;
import br.com.agroanalytics.simplexagro.domain.Talhao;
import br.com.agroanalytics.simplexagro.repository.PlantacaoRepository;
import br.com.agroanalytics.simplexagro.repository.TalhaoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/talhoes")
public class TalhaoController {

	@Autowired
	private TalhaoRepository talhaoRepository;

	@Autowired
	private PlantacaoRepository plantacaoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity criarTalhao(@RequestBody Talhao talhao) {

		talhaoRepository.save(talhao);

		return ResponseEntity.status(HttpStatus.CREATED).body(talhao);

	}

	@PostMapping("/cultivos")
	public ResponseEntity buscarCulturaComTalhao(@RequestBody Talhao talhao) {

		Plantacao plantacao;

		if (talhaoRepository.existsById(talhao.getId())) {

			plantacao = plantacaoRepository.findByTalhoes(talhao);
			
			return ResponseEntity.ok(plantacao);

		}

		return ResponseEntity.noContent().build();

	}

	@GetMapping
	public ResponseEntity<List<Talhao>> buscarTodosTalhoes() {

		if (talhaoRepository.count() > 0) {

			List<Talhao> talhao = talhaoRepository.findAll();

			return ResponseEntity.ok().body(talhao);

		}

		return ResponseEntity.noContent().build();

	}

	@GetMapping("/{id}")
	public ResponseEntity buscarCultura(@PathVariable("id") Long id) {

		Optional<Talhao> talhao = talhaoRepository.findById(id);

		if (talhao.isPresent()) {

			return ResponseEntity.ok().body(talhao);

		}

		return ResponseEntity.noContent().build();

	}

	@PutMapping("/{id}")
	public ResponseEntity atualizarCultura(@PathVariable("id") Long id, @RequestBody @Valid Talhao talhao) {

		if (talhaoRepository.findById(id) == null) {

			return ResponseEntity.noContent().build();

		}

		talhaoRepository.save(talhao);

		return ResponseEntity.ok(talhao);

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluirCultura(@PathVariable("id") Long id) {

		if (talhaoRepository.existsById(id)) {

			talhaoRepository.deleteById(id);

			return ResponseEntity.ok().build();

		} else {

			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping
	@Transactional
	public ResponseEntity excluirTodosTalhoes() {

		if (talhaoRepository.count() > 0) {

			talhaoRepository.deleteAll();

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.noContent().build();

	}

}
