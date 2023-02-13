package br.com.Challenge.Financeiro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.Challenge.Financeiro.model.Receita;
import br.com.Challenge.Financeiro.util.ReceitaRepository.ReceitaReppository;

@Service
public class ServiceReceita {

	
	private ReceitaReppository rRep;
	
	@Autowired
	public ServiceReceita(ReceitaReppository rRep) {
		this.rRep = rRep;
	}

	public Receita ReceitaValida(Receita recei) {
		var list = rRep.findAll();

		for (Receita rec : list) {
			if (rec.getData().getMonth() == recei.getData().getMonth() - 1
					&& rec.getDescricao().equals(recei.getDescricao())) {
				throw new IllegalArgumentException("Não é possível salvar uma receita com a mesma descrição no mesmo mês");
			}
		}
		return recei;
	}

	public Page<Receita> findPorData(Integer ano, Integer mes) {
		List<Receita> teste = new ArrayList<>();

		for (Receita list : rRep.findAll()) {
			if (list.getData().getMonth() == mes - 1) {
				teste.add(list);
			}

		}

		return new PageImpl<Receita>(teste);
	}
	
	public Page<Receita> listar(){
		return new PageImpl<Receita>(rRep.findAll());
	}
	
	public Page<Receita> listarPorDescricao(String descricao, Pageable page){
		return rRep.findAllReceitasByDescricao(page, descricao);
	}
	
	public Receita detalhar(Long id) {
		return rRep.getReferenceById(id);
	}
	
	public void deletar(Long id) {
		rRep.deleteById(id);
	}

	public void salvar(Receita rec) {
		rRep.save(ReceitaValida(rec));
	}
	
	

}