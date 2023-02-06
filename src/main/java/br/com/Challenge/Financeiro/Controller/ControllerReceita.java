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
import br.com.Challenge.Financeiro.util.ReceitaRepository.ReceitaReppository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/receitas")
public class ControllerReceita {

	@Autowired
	private ReceitaReppository rRep;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadatrar(@RequestBody @Valid ReceitaDTO dto, UriComponentsBuilder uriBuilder) throws ParseException {
		System.out.println("\\Cadastrando");
		
		var contt = new Receita(dto);
		rRep.save(contt);
				
		System.out.println("/Cadastrado");
		
		return ResponseEntity.created(uriBuilder.path("/receitas/{id}").buildAndExpand(contt.getId()).toUri()).body(new ReceitaDetalhamentoDTO(contt));
	}
	
	@GetMapping
	public ResponseEntity<Page<ReceitaListarDTO>> listarReceita(@PageableDefault(sort = {"data"}) Pageable page){
		System.out.println("***Listando***");
		
		return ResponseEntity.ok(rRep.findAll(page).map(ReceitaListarDTO::new));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReceitaDetalhamentoDTO> detalharReceita(@PathVariable Long id){
		System.out.println("***Detalhando***");
		
		return ResponseEntity.ok(new ReceitaDetalhamentoDTO(rRep.getReferenceById(id)));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDetalhamentoDTO> atualizarReceita(@PathVariable Long id, @RequestBody @Valid ReceitaDTO dto) throws ParseException {
		System.out.println("\\Atualizando");
		
		var rece = rRep.getReferenceById(id);
		
		rece.atualizarReceita(dto);
				
		System.out.println("/Atualizado");
		
		return ResponseEntity.ok(new ReceitaDetalhamentoDTO(rece));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletarReceita(@PathVariable Long id) {
		System.out.println("\\Deletando");
		
		rRep.deleteById(id);
		
		System.out.println("/Deletado");
		
		return ResponseEntity.noContent().build();
	}
	
}
