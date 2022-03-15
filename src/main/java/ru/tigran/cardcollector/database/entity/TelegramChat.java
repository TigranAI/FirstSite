package ru.tigran.cardcollector.database.entity;

import ru.tigran.cardcollector.database.converters.ChatTypeConverter;
import ru.tigran.cardcollector.enums.ChatType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "telegram_chats")
public class TelegramChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long chatId;

    @Column(nullable = false)
    @Convert(converter = ChatTypeConverter.class)
    private ChatType chatType;

    @Column(length = 1024, columnDefinition = "longtext")
    private String title;

    @Column(nullable = false)
    private Boolean isBlocked;

    @ManyToMany
    @JoinTable(name = "telegram_chat_user",
        joinColumns = @JoinColumn(name = "available_chats_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "members_id", nullable = false))
    private List<User> members;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
