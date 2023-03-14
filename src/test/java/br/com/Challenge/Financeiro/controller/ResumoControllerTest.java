package br.com.Challenge.Financeiro.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ResumoControllerTest {
	@Autowired
	private MockMvc mock;

	@Test
	public void shoudGet200FromListarResumoNoMes() throws Exception {
		mock.perform(MockMvcRequestBuilders.get("/resumo/{ano}/{mes}", 2000, 10))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
}
