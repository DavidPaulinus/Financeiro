package br.com.Challenge.Financeiro.DespesaRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DespesaRepositoryTest {
	
	@Autowired
	private DespesaRepository rep;
	
	@Test
	public void deveriaAcharDespesaPeloId() {
		var desp = rep.getReferenceById(1l);
		assertNotNull(desp);
	}
	
}
