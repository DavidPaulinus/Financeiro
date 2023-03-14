package br.com.Challenge.Financeiro.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReceitaDTO(
		@NotBlank
		String descricao,
		
		@NotNull
		Double valor, 
		
		@NotNull
		@JsonFormat(pattern = "dd/MM/yyy")
		LocalDate data) {

}
