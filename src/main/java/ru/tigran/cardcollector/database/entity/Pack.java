package ru.tigran.cardcollector.database.entity;

import org.springframework.transaction.annotation.Transactional;
import ru.tigran.cardcollector.Utilities;
import ru.tigran.cardcollector.functions.ListHelper;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "packs")
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 127, nullable = false)
    private String author;

    @Column(length = 512)
    private String description;

    @Column(nullable = false)
    private int priceCoins;

    @Column(nullable = false)
    private int priceGems;

    @Column(length = 127)
    private String previewFileId;

    private boolean isPreviewAnimated;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pack")
    private List<Sticker> stickers;

    private String cacheFilePath;

    @Column(nullable = false)
    private long openedCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        if (cacheFilePath != null) return cacheFilePath;
        if (previewFileId == null) return ListHelper.Random(getStickers()).getCacheForSaleFilePath();

        setCacheFilePath(Utilities.getTelegramFile(previewFileId, "/sticker/" + id));
        return cacheFilePath;
    }

    @Transactional
    public void setCacheFilePath(String cacheFilePath) {
        this.cacheFilePath = cacheFilePath;
    }
}
