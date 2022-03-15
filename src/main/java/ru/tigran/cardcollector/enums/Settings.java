package ru.tigran.cardcollector.enums;

public enum Settings {
    DailyTasks(0),
    ExpGain(1),
    StickerEffects(2),
    DailyTaskProgress(3),
    PiggyBankCapacity(4),
    DailyExpTop(5);

    private final int value;

    public int getValue() {
        return value;
    }

    Settings(int value) {
        this.value = value;
    }
}
