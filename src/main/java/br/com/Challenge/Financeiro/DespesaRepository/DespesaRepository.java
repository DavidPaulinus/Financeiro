package br.com.Challenge.Financeiro.DespesaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Challenge.Financeiro.model.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

}
