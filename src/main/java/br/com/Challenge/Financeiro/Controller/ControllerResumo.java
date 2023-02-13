package br.com.Challenge.Financeiro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Challenge.Financeiro.model.Resumo;
import br.com.Challenge.Financeiro.service.ServiceResumo;

@RestController
@RequestMapping("/resumo")
public class ControllerResumo {
	@Autowired
	private ServiceResumo service;

	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<Resumo> listarResumoMes(@PathVariable Integer ano, @PathVariable Integer mes) {
		return ResponseEntity.ok(service.resumo(ano, mes));
	}
}
