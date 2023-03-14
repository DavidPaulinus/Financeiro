package br.com.Challenge.Financeiro.model;

import java.text.ParseException;
import java.time.LocalDate;

import br.com.Challenge.Financeiro.DTO.DespesaDTO;
import br.com.Challenge.Financeiro.model.enums.Categorias;
import br.com.Challenge.Financeiro.util.Conversor;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tbdespesas")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Despesa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private Double valor;
	private LocalDate data;
	
	@Enumerated(EnumType.STRING)
	private Categorias categoria;

	public Despesa(DespesaDTO dto) throws ParseException {
		this.descricao = dto.descricao();
		this.valor = dto.valor();
		this.data = dto.data();
		this.categoria = Conversor.categoriaOutros(dto.categoria());
	}

	public void atualizarDespesa(@Valid DespesaDTO dto) throws ParseException {
		this.descricao = dto.descricao();
		this.valor = dto.valor();
		this.data = dto.data();
		this.categoria = Conversor.categoriaOutros(dto.categoria());
	}

}
