package br.com.Challenge.Financeiro.controller;

import java.net.URI;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ReceitaControllerTest {
	@Autowired
	private MockMvc mock;
	
	@Test
	public void shouldGet201ByCreatingDespesa() throws Exception {
		URI uri = new URI("/receitas");
		
		String json = "{\r\n"
				+ "\"descricao\": \"g\",\r\n"
				+ "\"valor\": 200.0,\r\n"
				+ "\"data\": \"12/10/2000\"\r\n"
				+ "}";
		
		mock.perform(MockMvcRequestBuilders.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}
	
	@Test
	public void shouldGet200FromFindAll() throws Exception {
		URI uri = new URI("/receitas");
		
		mock.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void shouldGet200FromFindById() throws Exception {		
		mock.perform(MockMvcRequestBuilders.get("/receitas/{id}", 3))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void shouldGet200FromFindByDescricao() throws Exception {		
		mock.perform(MockMvcRequestBuilders.get("/receitas/descricao/{descricao}", "a"))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void shouldGet200FromFindAllByAnoMes() throws Exception {		
		mock.perform(MockMvcRequestBuilders.get("/receitas/{ano}/{mes}", 2000, 10))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void shouldGet200FromPutMethod() throws Exception {	
		String json ="{\r\n"
				+ "\"descricao\": \"venda\",\r\n"
				+ "\"valor\": 200.0,\r\n"
				+ "\"data\": \"11/10/2000\"\r\n"
				+ "}";
		
		mock.perform(MockMvcRequestBuilders.put("/receitas/{id}", 3)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void shouldGet200FromDeletById() throws Exception {		
		mock.perform(MockMvcRequestBuilders.delete("/receitas/{id}", 3))
				.andExpect(MockMvcResultMatchers.status().is(204));
	}
}
