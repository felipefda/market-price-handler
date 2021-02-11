package com.santanderefx.rest;

import com.santanderefx.model.Price;
import com.santanderefx.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceRestTest {
    @Mock
    PriceService priceService;

    @InjectMocks
    private PriceRestController controller;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void prepare() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        this.controller = new PriceRestController(this.priceService);
    }

    @Test
    void testLatestPriceEndpoint() throws Exception {
        Price price = new Price(1L, "EUR/USD", new BigDecimal("100"), new BigDecimal("110"), new Date());
        when(priceService.getLatestPrice()).thenReturn(price);

        mockMvc.perform(get("/price/latestPrice")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void printLatestPriceEndpoint() throws Exception {
        Price price = new Price(1L, "EUR/USD", new BigDecimal("100"), new BigDecimal("110"), new Date());
        when(priceService.getLatestPrice()).thenReturn(price);

        MvcResult mvcResult = mockMvc.perform(get("/price/latestPrice"))
                .andReturn();

        System.out.println("The latest price is : " + mvcResult.getResponse().getContentAsString());

    }
}
