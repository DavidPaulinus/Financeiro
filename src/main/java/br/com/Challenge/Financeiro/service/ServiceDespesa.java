package br.com.Challenge.Financeiro.service;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.Challenge.Financeiro.model.Despesa;
import br.com.Challenge.Financeiro.util.Repository.DespesaRepository.DespesaRepository;

@Service
public class ServiceDespesa {
	
	private DespesaRepository dRep;
	
	@Autowired
	public ServiceDespesa(DespesaRepository dRep) {
		this.dRep = dRep;
	}
	
	public Despesa DespesaValida(Despesa dep) {
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

	public Page<Despesa> findPorData(Integer ano, Integer mes) {
		List<Despesa> teste = new ArrayList<>();

		for (Despesa list : dRep.findAll()) {
			if (list.getData().getMonth() == Month.of(mes-1)) {
				teste.add(list);
			}

		}

		return new PageImpl<Despesa>(teste);
	}

	public void salvar(Despesa desp) {
		dRep.save(DespesaValida(desp));
	}

	public Page<Despesa> listar() {
		return new PageImpl<Despesa>(dRep.findAll());
	}

	public Despesa detalhar(Long id) {
		return dRep.getReferenceById(id);
	}
	
	public Page<Despesa> findlistarPorDescricao(Pageable page, String descri){
		return dRep.findAllDespesasByDescricao(page, descri);
	}

	public void deletar(Long id) {
		dRep.deleteById(id);
	}
	
}
