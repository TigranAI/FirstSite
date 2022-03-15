package ru.tigran.cardcollector.enums;

public enum DailyTaskType {
    SendStickersToUsers(1);

    private final int value;

    public int getValue() {
        return value;
    }

    DailyTaskType(int value) {
        this.value = value;
    }
}
