package ru.tigran.cardcollector.database.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
public class User {
    @Id
    @Column(name = "id") public Long Id;
    @Column(name = "chat_id") public Long ChatId;
    @Column(name = "username") public String Username;
    @Column(name = "is_blocked") public Boolean IsBlocked;
    @Column(name = "privilege_level") public Integer PrivilegeLevel;

    @Transient public Cash cash;
    @Transient public UserLevel userLevel;
    @Transient public List<UserSticker> stickers;
}
