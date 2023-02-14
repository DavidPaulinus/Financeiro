package br.com.Challenge.Financeiro.controller;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DespesaControllerTest {
	@Autowired
	private MockMvc mock;

	@Test
	public void deveriaCadastrarDespesa() throws Exception {
		URI uri = new URI("/despesas");
		
		String json = "{\r\n"
				+ "\"descricao\": \"f\",\r\n"
				+ "\"valor\": 200.0,\r\n"
				+ "\"data\": \"12/10/2000\",\r\n"
				+ "\"categoria\": \"OUTRAS\"\r\n"
				+ "}";
		
		mock.perform(MockMvcRequestBuilders.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	public void shouldGet200FromFindAll() throws Exception {
		URI uri = new URI("/despesas");
		
		mock.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void shouldGet200FromFindById() throws Exception {		
		mock.perform(MockMvcRequestBuilders.get("/despesas/{id}", 1))
				.andExpect(MockMvcResultMatchers.status().is(200));
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
	public void shouldGet200FromPutaMethod() throws Exception {	
		String json ="{\r\n"
				+ "\"descricao\": \"venda de droga\",\r\n"
				+ "\"valor\": 200.0,\r\n"
				+ "\"data\": \"11/10/2000\"\r\n"
				+ "}";
		
		mock.perform(MockMvcRequestBuilders.put("/despesas/{id}", 1)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void shouldGet200FromDeletById() throws Exception {		
		mock.perform(MockMvcRequestBuilders.delete("/despesas/{id}", 1))
				.andExpect(MockMvcResultMatchers.status().is(204));
	}
	

}
