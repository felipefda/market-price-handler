package com.santanderefx.service;

import com.santanderefx.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PriceServiceTest {

    @Autowired
    PriceService priceService;

    @Test
    void onMessageTest() throws ParseException {
        String inputMessage = "106, EUR/USD, 100, 110, 01-06-2020 12:01:01:001";

        //send message
        priceService.onMessage(inputMessage);

        //get the last price
        Price price = priceService.getLatestPrice();

        assertNotNull(price);
        assertEquals(price.getId(), 106);
        assertEquals(price.getInstrumentName(), "EUR/USD");
        assertEquals(price.getBid(), new BigDecimal("99.9900"));
        assertEquals(price.getAsk(), new BigDecimal("110.0110"));
    }
}
