package ru.tigran.cardcollector.models;

public class TopDTO {
    private String username;
    private long value;
    private int position;

    public TopDTO(String username, long value) {
        this.username = username;
        this.value = value;
    }

    public TopDTO(String username, long value, int position) {
        this.username = username;
        this.value = value;
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public long getValue() {
        return value;
    }

    public int getPosition() {
        return position;
    }
}
