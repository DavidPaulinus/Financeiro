package br.com.Challenge.Financeiro.Controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Challenge.Financeiro.DTO.DespesaDTO;
import br.com.Challenge.Financeiro.DTO.DespesaDetalhamentoDTO;
import br.com.Challenge.Financeiro.DTO.DespesaListaDTO;
import br.com.Challenge.Financeiro.model.Despesa;
import br.com.Challenge.Financeiro.service.ServiceDespesa;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/despesas")
public class ControllerDespesa {
	@Autowired
	private ServiceDespesa service;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DespesaDetalhamentoDTO> cadastrarDespesa(@RequestBody @Valid DespesaDTO dto,
			UriComponentsBuilder uri) throws ParseException {

		var desp = new Despesa(dto);
		service.salvar(desp);

		return ResponseEntity.created(uri.path("/despesas/{id}").buildAndExpand(desp.getId()).toUri())
				.body(new DespesaDetalhamentoDTO(desp));
	}

	@GetMapping
	public ResponseEntity<Page<DespesaListaDTO>> listarDespesa(Pageable page) {
		return ResponseEntity.ok(service.listar().map(DespesaListaDTO::new));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DespesaDetalhamentoDTO> detalharDespesa(@PathVariable Long id) {
		return ResponseEntity.ok(new DespesaDetalhamentoDTO(service.detalhar(id)));
	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<Page<DespesaListaDTO>> listarRecitasByDecricao(@PathVariable String descricao,
			Pageable page) {
		return ResponseEntity.ok(service.findlistarPorDescricao(page, descricao).map(DespesaListaDTO::new));
	}

	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<Page<DespesaListaDTO>> listarDespesasPorMes(@PathVariable Integer ano,
			@PathVariable Integer mes) {
		return ResponseEntity.ok(service.findPorData(ano, mes).map(DespesaListaDTO::new));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DespesaDetalhamentoDTO> atualizarDespesa(@PathVariable Long id, @RequestBody @Valid DespesaDTO dto) throws ParseException {

		var desp = service.detalhar(id);
		desp.atualizarDespesa(dto);

		return ResponseEntity.ok(new DespesaDetalhamentoDTO(desp));
	}

	@DeleteMapping("{id}")
	public ResponseEntity deletarDespesa(@PathVariable Long id) {

		service.deletar(id);

		return ResponseEntity.noContent().build();
	}

}
