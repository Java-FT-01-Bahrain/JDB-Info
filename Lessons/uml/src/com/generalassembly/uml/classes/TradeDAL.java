package com.generalassembly.uml.classes;

public class TradeDAL {
    private final JdbcTemplate jdbcTemplate;

    public TradeDAL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveTradeDetails(Asset asset) {
    }
}
