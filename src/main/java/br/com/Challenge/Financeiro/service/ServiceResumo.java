package br.com.Challenge.Financeiro.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.Challenge.Financeiro.model.Despesa;
import br.com.Challenge.Financeiro.model.Receita;
import br.com.Challenge.Financeiro.model.Resumo;
import br.com.Challenge.Financeiro.model.TotalPorCategoria;

@Service
public class ServiceResumo {
	private ServiceDespesa despesa;
	private ServiceReceita receita;

	public ServiceResumo(ServiceDespesa despesa, ServiceReceita receita) {
		this.despesa = despesa;
		this.receita = receita;
	}

	public Resumo resumo(Integer ano, Integer mes) {
		var despServ = despesa.findPorData(ano, mes);
		var recServ = receita.findPorData(ano, mes);

		var totalDesp = valorTotalMesDespesa(despServ);
		var totalRec = valorTotalMesReceita(recServ);

		var saldoFinal = valorSaldoFinalMes(totalDesp, totalRec);

		var totalCategoria = totalPorCategoria(despServ);

		return new Resumo(totalRec, totalDesp, saldoFinal, totalCategoria);
	}

	private TotalPorCategoria totalPorCategoria(Page<Despesa> despServ) {
		TotalPorCategoria total = new TotalPorCategoria();

		despServ.forEach(x -> total.calculadoraCategoria(x.getCategoria(), x.getValor()));

		return total;
	}

	private Double valorSaldoFinalMes(Double totalDesp, Double totalRec) {
		return totalDesp + totalRec;
	}

	private Double valorTotalMesReceita(Page<Receita> recServ) {
		Double soma = 0d;

		for (Receita list : recServ) {
			soma += list.getValor();
		}

		return soma;
	}

	private Double valorTotalMesDespesa(Page<Despesa> despServ) {
		Double soma = 0d;

		for (Despesa list : despServ) {
			soma += list.getValor();
		}

		return soma;
	}
}
