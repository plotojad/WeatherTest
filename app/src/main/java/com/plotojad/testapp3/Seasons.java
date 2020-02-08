package com.plotojad.testapp3;

public enum  Seasons {
    WINTER("Зима"),
    SPRING("Весна"),
    SUMMER("Лето"),
    AUTUMN("Осень");

    private String name;

    Seasons(String name) {
    }

    public String getName() {
        return name;
    }
}
