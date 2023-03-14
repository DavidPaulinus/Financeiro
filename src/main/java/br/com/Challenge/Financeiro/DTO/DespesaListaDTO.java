package br.com.Challenge.Financeiro.DTO;

import java.time.LocalDate;

import br.com.Challenge.Financeiro.model.Despesa;
import br.com.Challenge.Financeiro.model.enums.Categorias;

public record DespesaListaDTO(String descricao, Double valor, LocalDate data, Categorias categoria) {
	public DespesaListaDTO(Despesa desp) {
		this(desp.getDescricao(), desp.getValor(), desp.getData(), desp.getCategoria());
	}
}
