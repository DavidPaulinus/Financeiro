package br.com.Challenge.Financeiro.DTO;

import java.util.Date;

import br.com.Challenge.Financeiro.model.Receita;

public record ReceitaListarDTO(String descricao, Double valor, Date data) {
	
	public ReceitaListarDTO(Receita rec) {
		this(rec.getDescricao(), rec.getValor(), rec.getData());
	}
}
