package ru.tigran.cardcollector.enums;

public enum ExclusiveTask {
    None(0),
    WinRoulette(1),
    CompleteDailyTask(2),
    ClaimLadderPrize(3),
    ClaimPuzzlePrize(4),
    ClaimGiveaway(5),
    GotGems(6),
    InviteFriends(7),
    SpendCoins(8);

    private final int value;

    public int getValue() {
        return value;
    }

    ExclusiveTask(int value) {
        this.value = value;
    }
}
