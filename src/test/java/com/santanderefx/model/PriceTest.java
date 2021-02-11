package com.santanderefx.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceTest {
    @Test
    void testCreatePrice() {
        Date timestamp = new Date();
        Price price = new Price(1L, "EUR/USD", new BigDecimal("100"), new BigDecimal("110"), timestamp);

        assertEquals(price.getId(), 1L);
        assertEquals(price.getInstrumentName(), "EUR/USD");
        assertEquals(price.getBid(), new BigDecimal("99.9900"));
        assertEquals(price.getAsk(), new BigDecimal("110.0110"));
        assertEquals(price.getTimestamp(), timestamp);
    }
}
