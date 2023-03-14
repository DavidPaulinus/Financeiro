package br.com.Challenge.Financeiro.controller;

import java.net.URI;
import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.Challenge.Financeiro.DTO.DespesaDTO;
import br.com.Challenge.Financeiro.model.enums.Categorias;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
public class DespesaControllerTest {
	@Autowired
	private MockMvc mock;

	@Autowired
	private JacksonTester<DespesaDTO> DespesaJson;

	@Test
	public void shouldGet201ByCreatingDespesa() throws Exception {
		URI uri = new URI("/despesas");

		var json = DespesaJson.write(new DespesaDTO("AAAA", 200.0, LocalDate.of(2000, 9, 10), Categorias.ALIMENTACAO)).getJson();

		mock.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	public void shouldGet400ByCreatingDespesaWithSameDescricaoInTheSameMonth() throws Exception {
		URI uri = new URI("/despesas");

		var json = DespesaJson.write(new DespesaDTO("AAAA", 200.0, LocalDate.of(2000, 9, 10), Categorias.ALIMENTACAO)).getJson();

		mock.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	public void shouldGet200FromFindAll() throws Exception {
		URI uri = new URI("/despesas");

		mock.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void shouldGet200FromFindById() throws Exception {
		mock.perform(MockMvcRequestBuilders.get("/despesas/{id}", 1)).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void shouldGet200FromFindByDescricao() throws Exception {
		mock.perform(MockMvcRequestBuilders.get("/despesas/descricao/{descricao}", "a"))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void shouldGet200FromFindAllByAnoMes() throws Exception {
		mock.perform(MockMvcRequestBuilders.get("/despesas/{ano}/{mes}", 2000, 10))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void shouldGet200FromPutMethod() throws Exception {
		var json = DespesaJson.write(new DespesaDTO("AAAAb", 200.0, LocalDate.of(2000, 9, 10), Categorias.ALIMENTACAO)).getJson();

		mock.perform(
				MockMvcRequestBuilders.put("/despesas/{id}", 1).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void shouldGet200FromDeletById() throws Exception {
		mock.perform(MockMvcRequestBuilders.delete("/despesas/{id}", 1))
				.andExpect(MockMvcResultMatchers.status().is(204));
	}

}
