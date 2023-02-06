package br.com.Challenge.Financeiro.DTO;

import java.util.Date;

import br.com.Challenge.Financeiro.model.Despesa;

public record DespesaListaDTO(String descricao, Double valor, Date data) {
	public DespesaListaDTO(Despesa desp) {
		this(desp.getDescricao(), desp.getValor(), desp.getData());
	}
}
