package br.com.bilac.sisdist.service;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bilac.sisdist.CardApplication;
import br.com.bilac.sisdist.domain.Card;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CardApplication.class})
public class CardServiceTest {

	@Autowired
	private CardService service;
	
	@Test
	public void shouldGetCardData() {
		Card card = service.getCard("DOCS-EN001");
		assertNotNull(card);
	}
}
