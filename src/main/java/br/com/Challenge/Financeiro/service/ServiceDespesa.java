package br.com.Challenge.Financeiro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.Challenge.Financeiro.DespesaRepository.DespesaRepository;
import br.com.Challenge.Financeiro.model.Despesa;
import br.com.Challenge.Financeiro.model.Receita;

public class ServiceDespesa {
	public Despesa DespesaValida(DespesaRepository dRep, Despesa dep) {
		var list = dRep.findAll();

		for (Despesa desp : list) {
			if (dep.getData().getMonth() == desp.getData().getMonth()
					&& dep.getDescricao().equals(desp.getDescricao())) {
				throw new IllegalArgumentException(
						"Não é possível cadastrar uma despesa com descrição igual a outra no mesmo mês");
			}
		}
		return dep;
	}
	
	public Page<Despesa> findPorData(DespesaRepository dRep, Integer ano, Integer mes) {
		List<Despesa> teste = new ArrayList<>();
		
		for (Despesa list : dRep.findAll()) {
			if (list.getData().getMonth() == mes - 1) {
				teste.add(list);
			}

		}
		
		return new PageImpl<Despesa>(teste);
	}
}
