package br.com.Challenge.Financeiro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.Challenge.Financeiro.model.Receita;
import br.com.Challenge.Financeiro.util.ReceitaRepository.ReceitaReppository;

public class ServiceReceita {

	public Receita ReceitaValida(ReceitaReppository rRep, Receita recei) {
		var list = rRep.findAll();

		for (Receita rec : list) {
			if (rec.getData().getMonth() == recei.getData().getMonth() - 1
					&& rec.getDescricao().equals(recei.getDescricao())) {
				return null;
			}
		}
		return recei;
	}

	public Page<Receita> findPorData(ReceitaReppository rRep, Integer ano, Integer mes) {
		List<Receita> teste = new ArrayList<>();
		
		for (Receita list : rRep.findAll()) {
			if (list.getData().getMonth() == mes - 1) {
				teste.add(list);
			}

		}
		
		return new PageImpl<Receita>(teste);
	}

}