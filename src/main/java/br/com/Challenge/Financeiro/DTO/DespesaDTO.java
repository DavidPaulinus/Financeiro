package br.com.Challenge.Financeiro.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DespesaDTO(
		@NotBlank
		String descricao,
		
		@NotNull
		Double valor, 
		
		@NotBlank
		String data){

}
