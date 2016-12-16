package com.icemobile.barcoders.data.domain;

public enum Product implements Poetic {
    MILK("1", "8713300801582"),
    BEER("2", "8710956001151"),
    COKE("3", "5449000000996"),
    CORNFLAKES("4", "5738001016915"),
    PEANUTBUTTER("5", "8722700462958"),
    NUTELLA("6", "3017620422003"),
    MUSTARD("7", "40198200"),
    NOODLES("8", "8850987123542"),
    PEPPER("9", "80273561"),
    COFFEE("10", "7640145298837");

    private String id;
    private String barcode;

    Product(String id, String barcode) {
        this.id = id;
        this.barcode = barcode;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getBarcode() {
        return barcode;
    }

}
