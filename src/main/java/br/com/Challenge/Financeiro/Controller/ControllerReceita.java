package br.com.Challenge.Financeiro.Controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import br.com.Challenge.Financeiro.DTO.ReceitaDTO;
import br.com.Challenge.Financeiro.DTO.ReceitaDetalhamentoDTO;
import br.com.Challenge.Financeiro.DTO.ReceitaListarDTO;
import br.com.Challenge.Financeiro.model.Receita;
import br.com.Challenge.Financeiro.service.ServiceReceita;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/receitas")
public class ControllerReceita {
	
	@Autowired
	private ServiceReceita service;

	@PostMapping
	@Transactional
	public ResponseEntity<ReceitaDetalhamentoDTO> cadatrar(@RequestBody @Valid ReceitaDTO dto,
			UriComponentsBuilder uriBuilder) throws ParseException {
		
		var rec = new Receita(dto);
		service.salvar(rec);

		return ResponseEntity.created(uriBuilder.path("/receitas/{id}").buildAndExpand(rec.getId()).toUri())
				.body(new ReceitaDetalhamentoDTO(rec));
	}

	@GetMapping
	public ResponseEntity<Page<ReceitaListarDTO>> listarReceita(@PageableDefault(sort = { "data" }) Pageable page) {
		return ResponseEntity.ok(service.listar().map(ReceitaListarDTO::new));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReceitaDetalhamentoDTO> detalharReceita(@PathVariable Long id) {
		return ResponseEntity.ok(new ReceitaDetalhamentoDTO(service.detalhar(id)));
	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<Page<ReceitaListarDTO>> listarDescricoesReceita(@PathVariable String descricao,Pageable page) {
		return ResponseEntity.ok(service.listarPorDescricao(descricao, page).map(ReceitaListarDTO::new));

	}
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<Page<ReceitaListarDTO>> listarReceitasPorMes(@PathVariable Integer ano, @PathVariable Integer mes, Pageable page){
		return ResponseEntity.ok(service.findPorData(ano, mes).map(ReceitaListarDTO::new));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDetalhamentoDTO> atualizarReceita(@PathVariable Long id,
			@RequestBody @Valid ReceitaDTO dto) throws ParseException {
		var rece = service.detalhar(id);

		rece.atualizarReceita(dto);

		return ResponseEntity.ok(new ReceitaDetalhamentoDTO(rece));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletarReceita(@PathVariable Long id) {
		service.deletar(id);

		return ResponseEntity.noContent().build();
	}

}
