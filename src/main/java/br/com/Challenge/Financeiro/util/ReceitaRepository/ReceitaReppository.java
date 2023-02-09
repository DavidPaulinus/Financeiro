package br.com.Challenge.Financeiro.util.ReceitaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.Challenge.Financeiro.model.Receita;

public interface ReceitaReppository extends JpaRepository<Receita, Long>{
	
	@Query("SELECT r FROM Receita r WHERE r.descricao = :descri")
	Page<Receita>findAllReceitasByDescricao(Pageable page,String descri);
}
