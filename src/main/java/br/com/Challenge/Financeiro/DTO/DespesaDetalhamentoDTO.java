package br.com.Challenge.Financeiro.DTO;

import java.util.Date;

import br.com.Challenge.Financeiro.enums.Categorias;
import br.com.Challenge.Financeiro.model.Despesa;

public record DespesaDetalhamentoDTO(String descricao, Double valor, Date data, Categorias categoria) {
	public DespesaDetalhamentoDTO(Despesa desp) {
		this(desp.getDescricao(), desp.getValor(), desp.getData(), desp.getCategoria());
	}
}
