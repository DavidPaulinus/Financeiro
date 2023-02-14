package br.com.Challenge.Financeiro.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.Challenge.Financeiro.enums.Categorias;

public class Conversor {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static Date toDate(String data) throws ParseException {
		return (Date) sdf.parse(data);
	}

	public static Categorias categoriaOutros(Categorias categoria) {
		if (categoria == null) {
			return Categorias.OUTRAS;

		} else {
			return categoria;
		}

	}

}
