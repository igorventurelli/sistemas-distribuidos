package br.com.bilac.sisdist.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bilac.sisdist.domain.Card;
import br.com.bilac.sisdist.exception.CardNotFoundException;
import br.com.bilac.sisdist.service.CardService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rest/cards")
public class CardController {
	
	private static final Logger LOGGER = Logger.getLogger(CardController.class);
	
	@Autowired
	private CardService service;
	
	@GetMapping("{cardCode}")
	public Card getCard(@PathVariable("cardCode") final String cardCode) throws CardNotFoundException {
		LOGGER.info(String.format("Recebi uma nova requisição! Card Code: %s", cardCode));
		
		Card result = service.getCard(cardCode);
		
		if(result == null) {
			LOGGER.error(String.format("Carta não encontrada: %s", cardCode));
			throw new CardNotFoundException();
		}
		
		LOGGER.info("Respondendo á requisição...");
		return result;
	}	

}
