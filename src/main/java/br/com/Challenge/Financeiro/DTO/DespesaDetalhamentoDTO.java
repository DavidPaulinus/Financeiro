package br.com.Challenge.Financeiro.DTO;

import java.time.LocalDate;

import br.com.Challenge.Financeiro.model.Despesa;
import br.com.Challenge.Financeiro.model.enums.Categorias;

public record DespesaDetalhamentoDTO(String descricao, Double valor, LocalDate data, Categorias categoria) {
	public DespesaDetalhamentoDTO(Despesa desp) {
		this(desp.getDescricao(), desp.getValor(), desp.getData(), desp.getCategoria());
	}
}
