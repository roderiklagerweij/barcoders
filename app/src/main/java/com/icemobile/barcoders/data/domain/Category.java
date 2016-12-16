package com.icemobile.barcoders.data.domain;

public enum Category implements Poetic {
    SPICE("a"),
    MORNING("b"),
    SUGAR("c"),
    DRINKS("d"),
    ENJOY("e"),
    SAVORY("f"),
    CREAMY("g");

    private String id;

    Category(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

}
