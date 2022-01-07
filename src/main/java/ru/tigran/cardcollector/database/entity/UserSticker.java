package ru.tigran.cardcollector.database.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_to_stickers_relations")
public class UserSticker {
    @Id
    @Column(name = "id") public long Id;
    @Column(name = "sticker_id") public String StickerId;
    @Column(name = "user_id") public long UserId;
    @Column(name = "count") public int Count;
    @Column(name = "payout") public Date Payout;
    @Column(name = "short_hash") public String ShortHash;
    @Column(name = "additional_data") public String AdditionalData;

    @ManyToOne
    @JoinColumn(name = "sticker_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Sticker sticker;

    public Boolean getAnimated(){
        return sticker.Animated;
    }

    public String getFilePath(){
        return sticker.getFilePath();
    }
}
