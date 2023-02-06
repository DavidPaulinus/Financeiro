package br.com.Challenge.Financeiro.Controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
import br.com.Challenge.Financeiro.DespesaRepository.DespesaRepository;
import br.com.Challenge.Financeiro.model.Despesa;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/despesas")
public class ControllerDespesa {
	
	@Autowired
	private DespesaRepository dRep;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrarDespesa(@RequestBody @Valid DespesaDTO dto, UriComponentsBuilder uri) throws ParseException {
		System.out.println("\\Cadastrando");
		
		var desp = new Despesa(dto);
		dRep.save(desp);
		
		System.out.println("/Cadastrado");
		
		return ResponseEntity.created(uri.path("/despesas/{id}").buildAndExpand(desp.getId()).toUri()
				).body(new DespesaDetalhamentoDTO(desp));
	}
	
	@GetMapping
	public ResponseEntity<Page<DespesaListaDTO>> listarDespesa(Pageable page){
		return ResponseEntity.ok(dRep.findAll(page).map(DespesaListaDTO::new));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DespesaDetalhamentoDTO> detalharDespesa(@PathVariable Long id) {
		return ResponseEntity.ok(new DespesaDetalhamentoDTO(dRep.getReferenceById(id)));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DespesaDetalhamentoDTO> atualizarDespesa(@PathVariable Long id, @RequestBody @Valid DespesaDTO dto) throws ParseException {
		System.out.println("\\Atualizando");
		
		var desp = dRep.getReferenceById(id);
		desp.atualizarDespesa(dto);
		
		System.out.println("/Atualizado");
		
		return ResponseEntity.ok(new DespesaDetalhamentoDTO(desp));
	}
	
}
