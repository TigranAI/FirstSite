package ru.tigran.cardcollector.database.entity;

import org.springframework.transaction.annotation.Transactional;
import ru.tigran.cardcollector.Utilities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "special_orders")
public class SpecialOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pack_id", nullable = false)
    private Pack pack;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean isInfinite;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private Integer priceCoins;

    @Column(nullable = false)
    private Integer priceGems;

    @Column(nullable = false)
    private Integer discount;

    @Column(nullable = false)
    private Boolean timeLimited;

    private LocalDateTime timeLimit;

    private String additionalPrize;

    private String description;

    @Column(columnDefinition = "text")
    private String previewFileId;

    private Boolean isPreviewAnimated;

    private String cacheFilePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Boolean isInfinite() {
        return isInfinite;
    }

    public void setInfinite(Boolean infinite) {
        isInfinite = infinite;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPriceCoins() {
        return priceCoins;
    }

    public void setPriceCoins(Integer priceCoins) {
        this.priceCoins = priceCoins;
    }

    public Integer getPriceGems() {
        return priceGems;
    }

    public void setPriceGems(Integer priceGems) {
        this.priceGems = priceGems;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean isTimeLimited() {
        return timeLimited;
    }

    public void setTimeLimited(Boolean timeLimited) {
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

    public Boolean isPreviewAnimated() {
        return isPreviewAnimated;
    }

    public void setPreviewAnimated(Boolean previewAnimated) {
        isPreviewAnimated = previewAnimated;
    }

    public String getCacheFilePath() {
        return cacheFilePath;
    }

    public void setCacheFilePath(String cacheFilePath) {
        this.cacheFilePath = cacheFilePath;
    }
}
