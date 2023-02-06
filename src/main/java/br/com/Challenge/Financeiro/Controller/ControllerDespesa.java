package br.com.Challenge.Financeiro.Controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.Challenge.Financeiro.DTO.DespesaDTO;
import br.com.Challenge.Financeiro.DTO.DespesaDetalhamentoDTO;
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
	public ResponseEntity cadastrarDespesa(@PathVariable @Valid DespesaDTO dto, UriComponentsBuilder uri) throws ParseException {
		System.out.println("\\Cadastrando");
		
		var desp = new Despesa(dto);
		dRep.save(desp);
		
		System.out.println("/Cadastrado");
		
		return ResponseEntity.created(uri.path("/despesas/{id}").buildAndExpand(desp.getId()).toUri()
				).body(new DespesaDetalhamentoDTO(desp));
	}
	
}
