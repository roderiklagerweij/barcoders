package com.icemobile.barcoders.data.domain;

public enum Category implements Poetic {
    SPICE(1),
    MORNING(2),
    SUGAR(3),
    DRINKS(4),
    ENJOY(5),
    SAVORY(6),
    CREAMY(7);

    private int id;

    Category(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

}
