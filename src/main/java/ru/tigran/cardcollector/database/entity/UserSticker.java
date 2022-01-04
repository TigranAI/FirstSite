package ru.tigran.cardcollector.database.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_to_stickers_relations")
public class UserSticker {
    @Id
    @Column public long Id;
    @Column public String StickerId;
    @Column(name = "user_id") public long UserId;
    @Column public int Count;
    @Column public Date Payout;
    @Column public String ShortHash;
    @Column public String AdditionalData;

    @Transient public String FilePath;
}
