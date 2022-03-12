package ru.tigran.cardcollector.database.entity;

import ru.tigran.cardcollector.Utilities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="packs")
public class Pack {
    @Id
    @Column(name="id") public Integer Id;
    @Column(name="author") public String Author;
    @Column(name="description") public String Description;
    @Column(name="opened_count") public Integer OpenedCount;
    @Column(name="sticker_preview") public String StickerPreview;
    @Column(name="animated") public Boolean Animated;
    @Column(name="sticker_preview_path") private String StickerPreviewPath;

    public String getStickerPreviewPath() {
        if (StickerPreviewPath == null) {
            if (StickerPreview != null) StickerPreviewPath = Utilities.getTelegramFile(StickerPreview, "stickers/" + Id);
        }
        return StickerPreviewPath;
    }

    public void setStickerPreviewPath(String stickerPreviewPath) {
        StickerPreviewPath = stickerPreviewPath;
    }
}
