package com.santanderefx.service;

import com.santanderefx.model.Price;

import java.text.ParseException;

public interface PriceService {
    void onMessage(String message) throws ParseException;
    Price getLatestPrice();
}
