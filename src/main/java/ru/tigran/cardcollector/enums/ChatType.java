package ru.tigran.cardcollector.enums;

public enum ChatType {
    Private(1),
    Group(2),
    Channel(3),
    Supergroup(4),
    Sender(5);

    private final int value;

    public int getValue() {
        return value;
    }

    ChatType(int value) {
        this.value = value;
    }
}
