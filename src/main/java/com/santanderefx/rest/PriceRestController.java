package com.santanderefx.rest;

import com.santanderefx.model.Price;
import com.santanderefx.service.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/price")
public class PriceRestController {
    private final PriceService priceService;

    public PriceRestController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping(value = "/latestPrice")
    public Price latestPrice() {
        return priceService.getLatestPrice();
    }
}
