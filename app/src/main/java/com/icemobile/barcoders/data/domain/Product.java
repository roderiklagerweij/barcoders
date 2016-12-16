package com.icemobile.barcoders.data.domain;

public enum Product implements Poetic {
    MILK(1, "123");
    // TODO fill in


    private int id;
    private String barcode;

    Product(int id, String barcode) {
        this.id = id;
        this.barcode = barcode;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getBarcode() {
        return barcode;
    }

}
