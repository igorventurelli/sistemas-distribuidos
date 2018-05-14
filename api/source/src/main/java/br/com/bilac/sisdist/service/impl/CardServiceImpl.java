package br.com.bilac.sisdist.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.bilac.sisdist.domain.Card;
import br.com.bilac.sisdist.domain.CardData;
import br.com.bilac.sisdist.domain.CardPrice;
import br.com.bilac.sisdist.domain.PriceData;
import br.com.bilac.sisdist.domain.PriceDetail;
import br.com.bilac.sisdist.domain.SetData;
import br.com.bilac.sisdist.domain.YGOPrices;
import br.com.bilac.sisdist.service.CardService;

@Service
public class CardServiceImpl implements CardService {

	private static final String PRICE_API_URL = "http://yugiohprices.com/api/price_for_print_tag/";
	private static final String PRINT_TAG_CODE_SEPARATOR = "-";
    private static final String API_CARD_LANGUAGE = "EN";
	
	private RestTemplate template;
	
	public CardServiceImpl() {
		template = new RestTemplate();
	}
	
	@Override
	public Card getCard(final String cardCode) {
		if(cardCode == null || cardCode.equals("")) { return null; }

        final String preparedPrintTag = prepareCardCode(cardCode);
        if(preparedPrintTag == null) { return null; }

        YGOPrices ygoPrices = template.getForObject(PRICE_API_URL + preparedPrintTag, YGOPrices.class);
        if(ygoPrices == null) { return null; }

        CardData cardData = ygoPrices.getCardData();
        if(cardData == null) { return null; }

        SetData setData = cardData.getSetData();
        if(setData == null) { return null; }

        PriceData priceData = setData.getPriceData();
        if(priceData == null) { return null; }

        PriceDetail priceDetail = priceData.getPriceDetail();
        if(priceDetail == null) { return null; }

        CardPrice cardPrice = priceDetail.getCardPrice();
        if(cardPrice == null) { return null; }

        if(!preparedPrintTag.equals(setData.getPrintTag())) { return null; }

        return new Card(
                cardData.getName(),
                cardPrice.getLowestPrice(),
                cardPrice.getHighestPrice(),
                cardPrice.getAveragePrice());
	}

	private String prepareCardCode(final String cardCode) {
		if(cardCode.indexOf(PRINT_TAG_CODE_SEPARATOR) == -1) { return null; }

        StringBuilder finalPrintTag = new StringBuilder(cardCode.substring(
                0, cardCode.indexOf(PRINT_TAG_CODE_SEPARATOR) + 1));

        finalPrintTag.append(API_CARD_LANGUAGE); //A API só tem dados de cartas em Inglês

        final String printTagSuffix = cardCode.substring(cardCode.indexOf(
                PRINT_TAG_CODE_SEPARATOR) + API_CARD_LANGUAGE.length() + 1);

        finalPrintTag.append(printTagSuffix);

        return finalPrintTag.toString().toUpperCase();
	}
}
