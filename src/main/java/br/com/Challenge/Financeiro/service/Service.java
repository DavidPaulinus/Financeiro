package br.com.Challenge.Financeiro.service;

import br.com.Challenge.Financeiro.DespesaRepository.DespesaRepository;
import br.com.Challenge.Financeiro.model.Despesa;
import br.com.Challenge.Financeiro.model.Receita;
import br.com.Challenge.Financeiro.util.ReceitaRepository.ReceitaReppository;

public class Service {

	public Despesa DespesaValida(DespesaRepository dRep, Despesa dep) {
		var list = dRep.findAll();

		for (Despesa desp : list) {
			if (dep.getData().getMonth() == desp.getData().getMonth()
					&& dep.getDescricao().equals(desp.getDescricao())) {
				return null;
			}
		}
		return dep;
	}

	public Receita ReceitaValida(ReceitaReppository dRep, Receita recei) {
		var list = dRep.findAll();

		for (Receita rec : list) {
			if (rec.getData().getMonth() == recei.getData().getMonth()
					&& rec.getDescricao().equals(recei.getDescricao())) {
				return null;
			}
		}
		return recei;
	}
}
