package br.com.Challenge.Financeiro.DTO;

import java.util.Date;

import br.com.Challenge.Financeiro.model.Receita;

public record ReceitaDetalhamentoDTO(String descricao, Double valor, Date data) {
	public ReceitaDetalhamentoDTO(Receita rec) {
		this(rec.getDescricao(), rec.getValor(), rec.getData());	
	};
}
