package br.com.agroanalytics.simplexagro.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import br.com.agroanalytics.simplexagro.domain.Colheita;
import br.com.agroanalytics.simplexagro.domain.Plantacao;
import br.com.agroanalytics.simplexagro.domain.Talhao;
import br.com.agroanalytics.simplexagro.repository.ColheitaRepository;
import br.com.agroanalytics.simplexagro.repository.PlantacaoRepository;
import br.com.agroanalytics.simplexagro.repository.TalhaoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/colheitas")
public class ColheitaController {

	@Autowired
	ColheitaRepository colheitaRepository;

	@Autowired
	TalhaoRepository talhaoRepository;

	@Autowired
	PlantacaoRepository plantacaoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity criarColheita(@RequestBody Colheita colheita) {

		Optional<Plantacao> plantacao;

		Optional<Talhao> canteiro;

		ArrayList<Talhao> talhao;

		int contador = 0;

		talhao = new ArrayList<Talhao>();

		plantacao = plantacaoRepository.findById(colheita.getPlantacao().getId());

		if (plantacao.isPresent()) {

			talhao.addAll(plantacao.get().getTalhoes());

			for (Talhao a : talhao) {

				talhaoRepository.mudarEstado(true, a.getId());

			}

		} else {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
					"Para realizar a colheita você deve já ter cadastrado uma plantacão com cultura e o talhao, e o talhão não pode estar envolvido em nenhuma outra plantação!");

		}

		colheitaRepository.save(colheita);

		return ResponseEntity.status(HttpStatus.CREATED).body("Talhões liberados!");

	}

	@GetMapping
	public ResponseEntity<List<Colheita>> buscarTodasColheitas() {

		if (plantacaoRepository.count() > 0) {

			List<Colheita> colheita = colheitaRepository.findAll();

			return ResponseEntity.ok(colheita);

		}

		return ResponseEntity.noContent().build();

	}

	@GetMapping("/{id}")
	public ResponseEntity buscarColheita(@PathVariable("id") Long id) {

		Optional<Colheita> colheita = colheitaRepository.findById(id);

		if (colheita.isPresent()) {

			return ResponseEntity.ok().body(colheita);

		}

		return ResponseEntity.noContent().build();

	}

	@PutMapping
	public ResponseEntity atualizarColheita(@RequestBody Colheita colheita) {

		Optional<Colheita> safra = colheitaRepository.findById(colheita.getId());

		if (safra.isPresent()) {

			colheitaRepository.save(colheita);

			return ResponseEntity.status(HttpStatus.CREATED).body(colheita + "/n Talhão Liberado!");

		} else {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
					"Para realizar a alteração da colheita você deve já ter cadastrado uma plantacão com cultura e o talhao, e o talhão não pode estar envolvido em nenhuma outra plantação!");

		}

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluirColheita(@PathVariable("id") Long id) {

		if (colheitaRepository.existsById(id)) {

			colheitaRepository.deleteById(id);

			return ResponseEntity.ok().build();

		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body("Para excluir uma colheita, esta colheita deve existir.");

	}

	@DeleteMapping
	@Transactional
	public ResponseEntity excluirTodasColheitas() {

		if (plantacaoRepository.count() > 0) {

			plantacaoRepository.deleteAll();

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.noContent().build();

	}

}
