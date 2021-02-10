package br.com.agroanalytics.simplexagro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agroanalytics.simplexagro.domain.Cultura;
import br.com.agroanalytics.simplexagro.domain.GraficoInsumos;
import br.com.agroanalytics.simplexagro.repository.GraficoInsumosRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/graficos")
public class GraficoInsumosController {

	
	@Autowired
	private GraficoInsumosRepository graficoInsumosRepository;
	
	@GetMapping
	public ResponseEntity<List<GraficoInsumos>> buscarTodosgraficos(){
		
		if (graficoInsumosRepository.count() > 0) {

			List<GraficoInsumos> graficos = graficoInsumosRepository.findAll();

			return ResponseEntity.ok(graficos);

		}

		return ResponseEntity.noContent().build();
		
		
		
	}
}
