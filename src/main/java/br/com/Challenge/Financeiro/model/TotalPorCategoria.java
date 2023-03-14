package br.com.Challenge.Financeiro.model;

import br.com.Challenge.Financeiro.model.enums.Categorias;
import lombok.Getter;

@Getter
public class TotalPorCategoria {
	private Double alimentacao = 0d;
	private Double saude = 0d;
	private Double moradia = 0d;
	private Double transporte = 0d;
	private Double educacao = 0d;
	private Double lazer = 0d;
	private Double imprevistos = 0d;
	private Double outros = 0d;

	public void calculadoraCategoria(Categorias categoria, Double valor) {
		if (categoria.equals(Categorias.ALIMENTACAO))
			alimentacao += valor;
		else if (categoria.equals(Categorias.SAUDE))
			saude += valor;
		else if (categoria.equals(Categorias.MORADIA))
			moradia += valor;
		else if (categoria.equals(Categorias.TRANSPORTE))
			transporte += valor;
		else if (categoria.equals(Categorias.EDUCACAO))
			educacao += valor;
		else if (categoria.equals(Categorias.LAZER))
			lazer += valor;
		else if (categoria.equals(Categorias.IMPREVISTOS))
			imprevistos += valor;
		else if (categoria.equals(Categorias.OUTRAS))
			outros += valor;
	}
}
