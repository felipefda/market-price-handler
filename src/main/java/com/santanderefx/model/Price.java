package com.santanderefx.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Objects;

public class Price {
    Long id;
    String instrumentName;
    BigDecimal bid;
    BigDecimal ask;
    Date timestamp;
    private final BigDecimal commission = BigDecimal.valueOf(0.01);
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public Price(Long id, String instrumentName, BigDecimal bid, BigDecimal ask, Date timestamp) {
        this.id = id;
        this.instrumentName = instrumentName;
        this.bid = bid.subtract(getCommission(bid));
        this.ask = ask.add(getCommission(ask));
        this.timestamp = timestamp;
    }

    private BigDecimal getCommission(BigDecimal price) {
        return price.multiply(commission).divide(ONE_HUNDRED, 4, RoundingMode.UP);
    }

    public Long getId() {
        return id;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(id, price.id) && Objects.equals(instrumentName, price.instrumentName) && Objects.equals(bid, price.bid) && Objects.equals(ask, price.ask) && Objects.equals(timestamp, price.timestamp) && Objects.equals(commission, price.commission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instrumentName, bid, ask, timestamp, commission);
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", instrumentName='" + instrumentName + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", timestamp=" + timestamp +
                ", commission=" + commission +
                '}';
    }
}
