package com.generalassembly.uml.classes;

public class TradingMain {
    private final TradeDAL TradeDAL = new TradeDAL(new JdbcTemplate());

    public void bookTrade(Asset asset) {
    }

    protected void saveTradeDetails() {
    }
}
