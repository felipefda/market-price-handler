package com.santanderefx.service.impl;

import com.santanderefx.model.Price;
import com.santanderefx.service.PriceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class PriceServiceImpl implements PriceService {
    List<Price> priceList = new ArrayList<>();

    public PriceServiceImpl() {
        //start one price for latest endpoint
        Date timestamp = new Date();
        Price price = new Price(1L, "EUR/USD", new BigDecimal("100"), new BigDecimal("110"), timestamp);
        this.priceList.add(price);
    }

    @Override
    public void onMessage(String message) throws ParseException {
        this.priceList.add(getPriceFromString(getStringLine(message)));
    }

    @Override
    public Price getLatestPrice() {
        return priceList.stream().reduce((first, second) -> second)
                .orElse(null);
    }

    private Price getPriceFromString(String[] input) throws ParseException {
        Long id = Long.valueOf(input[0]);
        String instrumentName = input[1].replace(" ","");
        BigDecimal bid = new BigDecimal(input[2].replace(" ",""));
        BigDecimal ask = new BigDecimal(input[3].replace(" ",""));
        Date timestamp = getDateFromInputString(input[4]);
        return new Price(id, instrumentName, bid, ask, timestamp);
    }

    private Date getDateFromInputString(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
        return formatter.parse(date);
    }

    private String[] getStringLine(String input) {
        return input.split(",");
    }
}
