package ru.tigran.cardcollector.enums;

public enum PrivilegeLevel {
    User(0),
    Vip(2),
    Artist(4),
    Tester(6),
    Programmer(7),
    Admin(9),
    Owner(10);

    private final int value;

    public int getValue() {
        return value;
    }

    PrivilegeLevel(int value) {
        this.value = value;
    }
}
