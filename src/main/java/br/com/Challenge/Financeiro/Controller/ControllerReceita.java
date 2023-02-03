package br.com.Challenge.Financeiro.Controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Challenge.Financeiro.DTO.ReceitaDTO;
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
}
