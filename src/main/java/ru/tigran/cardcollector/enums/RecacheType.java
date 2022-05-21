package ru.tigran.cardcollector.enums;

public enum RecacheType {
    UploadPack(0),
    UploadPackPreview(1),
    UploadPackGifPreview(2),
    UploadSticker(3),
    UploadForSaleSticker(4);
    private final int value;

    public int getValue() {
        return value;
    }

    RecacheType(int value) {
        this.value = value;
    }
}
