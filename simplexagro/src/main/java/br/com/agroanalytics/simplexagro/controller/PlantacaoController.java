package br.com.agroanalytics.simplexagro.controller;

import java.text.ParseException;
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

import br.com.agroanalytics.simplexagro.domain.Cultura;
import br.com.agroanalytics.simplexagro.domain.GraficoInsumos;
import br.com.agroanalytics.simplexagro.domain.Insumo;
import br.com.agroanalytics.simplexagro.domain.Plantacao;
import br.com.agroanalytics.simplexagro.domain.Talhao;
import br.com.agroanalytics.simplexagro.repository.CulturaRepository;
import br.com.agroanalytics.simplexagro.repository.GraficoInsumosRepository;
import br.com.agroanalytics.simplexagro.repository.InsumoRepository;
import br.com.agroanalytics.simplexagro.repository.PlantacaoRepository;
import br.com.agroanalytics.simplexagro.repository.TalhaoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/plantacoes")
public class PlantacaoController {

	@Autowired
	private PlantacaoRepository plantacaoRepository;

	@Autowired
	private InsumoRepository insumoRepository;

	@Autowired
	private CulturaRepository culturaRepository;

	@Autowired
	private TalhaoRepository talhaoRepository;

	@Autowired
	private GraficoInsumosRepository graficoInsumosRepository;

	@PostMapping
	@Transactional
	public ResponseEntity criarPlantacao(@RequestBody Plantacao plantacao) throws ParseException {

		GraficoInsumos graficoInsumos;

		ArrayList<Talhao> talhao;

		ArrayList<Insumo> insumos;

		Cultura cultura = null;

		graficoInsumos = new GraficoInsumos();

		insumos = new ArrayList<Insumo>();

		talhao = new ArrayList<Talhao>();

		talhao.addAll(plantacao.getTalhoes());

		insumos.addAll(plantacao.getInsumos());

		for (Insumo i : insumos) {

			if (insumoRepository.existsById(i.getId()) == false) {

				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("O insumo id: " + i.getId()
						+ ", não foi encontrado! Criar o insumo antes de aplicar ele em uma plantação.");

			}

		}

		for (Talhao a : talhao) {

			if (talhaoRepository.existsById(a.getId()) == false) {

				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("O talhao id: " + a.getId()
						+ ", não foi encontrado! Cadastre o talhão antes de associar ele em uma plantação.");

			}
			if (talhaoRepository.buscarDisponibilidade(a.getId()) == false) {

				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("Deu errado" + plantacaoRepository.findByTalhoes(a));

			}
		}

		if (culturaRepository.existsById(plantacao.getCultura().getId())) {

			for (Talhao a : talhao) {

				talhaoRepository.mudarEstado(false, a.getId());

			}

		} else {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("A cultura id: " + plantacao.getCultura()
					+ " não foi encontrada! Criar a cultura antes de aplicar ela em uma plantação.");
		}

		plantacao.calcularDataColheita(culturaRepository.buscarMaturacaoPorId(plantacao.getCultura().getId()));

		int tempo = 0;

		for (Insumo i : insumos) {

			if (insumoRepository.buscarTempoAcaoPorId(i.getId()) > tempo)

				plantacao.calcularDataConsumo(insumoRepository.buscarTempoAcaoPorId(i.getId()));

			tempo = insumoRepository.buscarTempoAcaoPorId(i.getId());

		}
		
		plantacaoRepository.save(plantacao);

		graficoInsumos.setData(plantacao.getDataCriacao());

		graficoInsumos.setQuantLitrosAplicados(plantacao.getQuantLitrosAplicados());

		graficoInsumos.setTotalInsumosGastos(plantacaoRepository.buscarTodosInsumosAplicados());
		
		for (Insumo a : insumos) {

			graficoInsumos.setQuantInsumosTotal(insumoRepository.buscarVolumeRecipiente(a.getId()) - plantacao.getQuantLitrosAplicados());

		}

		graficoInsumosRepository.save(graficoInsumos);

		return ResponseEntity.status(HttpStatus.CREATED).body(plantacao);

	}

	@GetMapping
	public ResponseEntity<List<Plantacao>> buscarTodasPlantacoes() {

		if (plantacaoRepository.count() > 0) {

			List<Plantacao> plantacao = plantacaoRepository.findAll();

			return ResponseEntity.ok().body(plantacao);

		}

		return ResponseEntity.noContent().build();

	}

	@GetMapping("/{id}")
	public ResponseEntity buscarPlantacao(@PathVariable("id") Long id) {

		Optional<Plantacao> plantacao = plantacaoRepository.findById(id);

		if (plantacao.isPresent()) {

			return ResponseEntity.ok().body(plantacao);

		}

		return ResponseEntity.noContent().build();

	}

	@PutMapping("/{id}")
	public ResponseEntity atualizarPlantacao(@PathVariable("id") Long id, @RequestBody Plantacao plantacao) {

		Plantacao cultivo = plantacaoRepository.findById(id).orElse(null);

		if (cultivo != null) {

			plantacaoRepository.save(plantacao);

			return ResponseEntity.ok(plantacao);

		}

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluirPlantacao(@PathVariable("id") Long id) {

		if (plantacaoRepository.existsById(id)) {

			plantacaoRepository.deleteById(id);

			return ResponseEntity.ok().build();

		} else {

			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping
	@Transactional
	public ResponseEntity excluirTodasPlantacoes() {

		if (plantacaoRepository.count() > 0) {

			plantacaoRepository.deleteAll();

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.noContent().build();

	}

}
