package br.com.Challenge.Financeiro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Resumo {
	private Double totalMesReceita;
	private Double totalMesDespesa;
	private Double saldoFinal;
	private TotalPorCategoria totalPorCategoria; 
}
