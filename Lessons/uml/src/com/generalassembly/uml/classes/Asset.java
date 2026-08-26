package com.generalassembly.uml.classes;

public class Asset {

    private int id;
    private double shares;

    public int getId() {
        return id;
    }

    public double getShares() {
        return shares;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    private double purchasePrice;
}
