package br.com.Challenge.Financeiro.util;

import br.com.Challenge.Financeiro.model.enums.Categorias;

public class Conversor {

	public static Categorias categoriaOutros(Categorias categoria) {
		if (categoria == null) {
			return Categorias.OUTRAS;

		} else {
			return categoria;
		}

	}

}
