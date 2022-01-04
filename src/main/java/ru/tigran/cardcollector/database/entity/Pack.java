package ru.tigran.cardcollector.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name="packs")
public class Pack {
    @Id
    @Column(name="id") public Integer Id;
    @Column(name="author") public String Author;
    @Column(name="description") public String Description;
    @Column(name="opened_count") public Integer OpenedCount;
    @Column(name="sticker_preview") public String StickerPreview;
    @Column(name="animated") public Boolean Animated;

    @Transient public String StickerPreviewPath;
}
