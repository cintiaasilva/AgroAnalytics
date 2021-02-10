package br.com.agroanalytics.simplexagro.controller;

import java.lang.module.FindException;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

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
import br.com.agroanalytics.simplexagro.domain.Oferta;
import br.com.agroanalytics.simplexagro.repository.ColheitaRepository;
import br.com.agroanalytics.simplexagro.repository.OfertaRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ofertas")
public class OfertaController {

	@Autowired
	private OfertaRepository ofertaRepository;
	
	@Autowired
	private ColheitaRepository colheitaRepository;

	private static Stack<Double> ofertas = new Stack<Double>();

	@PostMapping
	public ResponseEntity lanceUsuario(@RequestBody Oferta oferta) {

		if (ofertas.isEmpty() == true || ofertas.peek() < oferta.getValorOferta()) {

			ofertas.push(oferta.getValorOferta());

			ofertaRepository.save(oferta);

			return ResponseEntity.status(HttpStatus.CREATED).body(oferta);

		}

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("O valor da ultima oferta Ã© maior ou igual ao que vocÃª estÃ¡ ofertando!");

	}
	
	@PostMapping("/aceitarOferta")
	public ResponseEntity aceitarOferta(@RequestBody Long idOferta) {
		
		if (ofertaRepository.existsById(idOferta) == true && colheitaRepository.existsById(idOferta) == true) { 
		
			Optional<Oferta> oferta = ofertaRepository.findById(idOferta);
			
			Optional<Colheita> colheita = colheitaRepository.findById(oferta.get().getIdColheita());
			
			colheitaRepository.mudarEstado(colheitaRepository.buscarCaixas(colheita.get().getId()) - oferta.get().getQuantCaixasCompradas(), oferta.get().getIdColheita());
			
			return ResponseEntity.status(HttpStatus.OK).body("Negócio fechado!");

		}else {

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Oferta ou Lance com id's inválidos!");
		
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Oferta>> buscarTodasOfertas() {

		if (ofertaRepository.count() > 0) {

			List<Oferta> ofertas = ofertaRepository.findAll();

			return ResponseEntity.ok(ofertas);

		}

		return ResponseEntity.noContent().build();

	}

	@GetMapping("/{id}")
	public ResponseEntity buscarOferta(@PathVariable("id") Long id) {

		Optional<Oferta> oferta = ofertaRepository.findById(id);

		if (oferta.isPresent()) {

			return ResponseEntity.ok().body(oferta);

		}

		return ResponseEntity.noContent().build();

	}

	@PutMapping
	public ResponseEntity atualizarOferta(@RequestBody Oferta oferta) {

		if (ofertaRepository.findById(oferta.getId()) == null) {

			return ResponseEntity.noContent().build();

		}

		ofertaRepository.save(oferta);

		return ResponseEntity.ok(oferta);

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluirOferta(@PathVariable("id") Long id) {

		if (ofertaRepository.existsById(id)) {

			ofertaRepository.deleteById(id);

			return ResponseEntity.ok().build();
			
		} else {

			return ResponseEntity.notFound().build();
		}

	}

}