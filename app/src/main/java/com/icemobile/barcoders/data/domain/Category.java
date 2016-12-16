package com.icemobile.barcoders.data.domain;

public enum Category implements Poetic {
    DRINK(1);
    // TODO fill in

    private int id;

    Category(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

}
