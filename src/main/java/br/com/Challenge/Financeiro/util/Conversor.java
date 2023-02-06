package br.com.Challenge.Financeiro.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Conversor {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public static Date toDate(String data) throws ParseException {
		return (Date) sdf.parse(data);
	}

}
