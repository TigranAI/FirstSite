package ru.tigran.cardcollector.enums;

public enum Prize {
    RandomSticker(0),
    SelectedSticker(1),
    RandomPack(2);

    private final int value;

    public int getValue() {
        return value;
    }

    Prize(int value) {
        this.value = value;
    }
}
