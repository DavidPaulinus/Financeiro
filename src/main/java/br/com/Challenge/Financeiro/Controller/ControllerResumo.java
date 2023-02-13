package br.com.Challenge.Financeiro.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo")
public class ControllerResumo {

	@GetMapping("/{ano}/{mes}")
	public ResponseEntity listarResumoMes(@PathVariable String ano, @PathVariable String mes) {
		return ResponseEntity.ok().build();
	}
}
