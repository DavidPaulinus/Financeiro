package br.com.Challenge.Financeiro.DTO;

import java.time.LocalDate;

import br.com.Challenge.Financeiro.model.Receita;

public record ReceitaDetalhamentoDTO(String descricao, Double valor, LocalDate data) {
	public ReceitaDetalhamentoDTO(Receita rec) {
		this(rec.getDescricao(), rec.getValor(), rec.getData());	
	};
}
