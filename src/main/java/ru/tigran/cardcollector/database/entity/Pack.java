package ru.tigran.cardcollector.database.entity;

import ru.tigran.cardcollector.functions.ListHelper;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "packs")
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 127, nullable = false)
    private String author;

    @Column(length = 512)
    private String description;

    @Column(nullable = false)
    private Integer priceCoins;

    @Column(nullable = false)
    private Integer priceGems;

    @Column(length = 127)
    private String previewFileId;

    private Boolean isPreviewAnimated;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pack")
    @OrderBy("tier")
    private List<Sticker> stickers;

    private String cacheFilePath;

    @Column(nullable = false)
    private long openedCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public long getOpenedCount() {
        return openedCount;
    }

    public void setOpenedCount(long openedCount) {
        this.openedCount = openedCount;
    }

    public List<Sticker> getStickers() {
        return stickers;
    }

    public void setStickers(List<Sticker> stickers) {
        this.stickers = stickers;
    }


    public String getCacheFilePath() {
        if (previewFileId == null) return ListHelper.Random(getStickers()).getCacheForSaleFilePath();

        return cacheFilePath;
    }

    public void setCacheFilePath(String cacheFilePath) {
        this.cacheFilePath = cacheFilePath;
    }
}
