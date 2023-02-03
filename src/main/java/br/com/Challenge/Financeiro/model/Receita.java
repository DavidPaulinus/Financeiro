package br.com.Challenge.Financeiro.model;

import java.util.Date;
import java.text.ParseException;

import br.com.Challenge.Financeiro.DTO.ReceitaDTO;
import br.com.Challenge.Financeiro.uitl.Conversor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tbreceitas")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Receita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private Double valor;
	private Date data;
	
	public Receita(ReceitaDTO dto) throws ParseException {
		this.descricao = dto.descricao();
		this.valor = dto.valor();
		this.data = Conversor.toDate(dto.data());
	}
	
	
}
