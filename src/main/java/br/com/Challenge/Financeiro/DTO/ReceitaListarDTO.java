package br.com.Challenge.Financeiro.DTO;

import java.time.LocalDate;

import br.com.Challenge.Financeiro.model.Receita;

public record ReceitaListarDTO(String descricao, Double valor, LocalDate data) {
	
	public ReceitaListarDTO(Receita rec) {
		this(rec.getDescricao(), rec.getValor(), rec.getData());
	}
}
