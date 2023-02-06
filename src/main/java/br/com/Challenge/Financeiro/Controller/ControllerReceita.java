package br.com.Challenge.Financeiro.Controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public void cadatrar(@RequestBody @Valid ReceitaDTO dto) throws ParseException {
		System.out.println("\\Cadastrando");
		
		rRep.save(new Receita(dto));
		
		System.out.println("/Cadastrado");
	}
	
	@GetMapping
	public ResponseEntity<Page<ReceitaListarDTO>> listarReceita(@PageableDefault(sort = {"data"}) Pageable page){
		System.out.println("***Listando***");
		
		return ResponseEntity.ok(rRep.findAll(page).map(ReceitaListarDTO::new));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReceitaDetalhamentoDTO> detalharReceitar(@PathVariable Long id){
		System.out.println("***Detalhamento***");
		
		return ResponseEntity.ok(new ReceitaDetalhamentoDTO(rRep.getReferenceById(id)));
	}
	
}
