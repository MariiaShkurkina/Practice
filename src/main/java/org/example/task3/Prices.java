package org.example.task3;

public enum Prices {
    ECONOMY(100),
    STANDARD(200),
    LUX(500),
    ULTRA_LUX(1000);

    final int price;

    Prices(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}