package ru.tigran.cardcollector.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name="stickers")
public class Sticker {
    @Id
    @Column(name = "id") public String Id;
    @Column(name = "title") public String Title;
    @Column(name = "author") public String Author;
    @Column(name = "income") public Integer Income;
    @Column(name = "income_time") public Integer IncomeTime;
    @Column(name = "tier") public Integer Tier;
    @Column(name = "emoji") public String Emoji;
    @Column(name = "description") public String Description;
    @Column(name = "md5hash") public String MD5Hash;
    @Column(name = "effect") public String Effect;
    @Column(name = "pack_id") public Integer PackId;

    @Transient public String FilePath;

    public String getStarTier(){
        String result = new String(new char[Tier]);
        return result.replace('\0', '⭐');
    }
}
