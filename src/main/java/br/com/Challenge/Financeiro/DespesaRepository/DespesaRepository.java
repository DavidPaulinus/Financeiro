package br.com.Challenge.Financeiro.DespesaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.Challenge.Financeiro.model.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
	
	@Query("SELECT d FROM Despesa d WHERE d.descricao = :descri")
	Page<Despesa> findAllDespesasByDescricao(Pageable page, String descri);
}
