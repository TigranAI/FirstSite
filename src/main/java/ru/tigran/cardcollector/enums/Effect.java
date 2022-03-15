package ru.tigran.cardcollector.enums;

public enum Effect {
    None(0),
    PiggyBank200(1),
    Diamonds25Percent(2),
    AuctionDiscount5(3),
    Random1Pack5Day(4),
    RandomSticker2Tier3Day(5),
    RandomSticker1Tier2Day(6);

    private final int value;

    public int getValue() {
        return value;
    }

    Effect(int value) {
        this.value = value;
    }
}
