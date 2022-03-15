package ru.tigran.cardcollector.database.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "special_orders")
public class SpecialOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pack_id", nullable = false)
    private Pack pack;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean isInfinite;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private int priceCoins;

    @Column(nullable = false)
    private int priceGems;

    @Column(nullable = false)
    private int discount;

    @Column(nullable = false)
    private boolean timeLimited;

    private LocalDateTime timeLimit;

    private String additionalPrize;

    private String description;

    @Column(columnDefinition = "text")
    private String previewFileId;

    private boolean isPreviewAnimated;

    private String cacheFilePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isInfinite() {
        return isInfinite;
    }

    public void setInfinite(boolean infinite) {
        isInfinite = infinite;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPriceCoins() {
        return priceCoins;
    }

    public void setPriceCoins(int priceCoins) {
        this.priceCoins = priceCoins;
    }

    public int getPriceGems() {
        return priceGems;
    }

    public void setPriceGems(int priceGems) {
        this.priceGems = priceGems;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isTimeLimited() {
        return timeLimited;
    }

    public void setTimeLimited(boolean timeLimited) {
        this.timeLimited = timeLimited;
    }

    public LocalDateTime getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(LocalDateTime timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getAdditionalPrize() {
        return additionalPrize;
    }

    public void setAdditionalPrize(String additionalPrize) {
        this.additionalPrize = additionalPrize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreviewFileId() {
        return previewFileId;
    }

    public void setPreviewFileId(String previewFileId) {
        this.previewFileId = previewFileId;
    }

    public boolean isPreviewAnimated() {
        return isPreviewAnimated;
    }

    public void setPreviewAnimated(boolean previewAnimated) {
        isPreviewAnimated = previewAnimated;
    }

    public String getCacheFilePath() {
        return cacheFilePath;
    }

    public void setCacheFilePath(String cacheFilePath) {
        this.cacheFilePath = cacheFilePath;
    }
}
