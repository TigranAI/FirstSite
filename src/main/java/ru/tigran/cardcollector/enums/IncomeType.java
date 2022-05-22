package ru.tigran.cardcollector.enums;

public enum IncomeType {
    Coins(0),
    Gems(1),
    Candies(2);

    private final int value;

    public int getValue() {
        return value;
    }

    IncomeType(int value) {
        this.value = value;
    }
}
