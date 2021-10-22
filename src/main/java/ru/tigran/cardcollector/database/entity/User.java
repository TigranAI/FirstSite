package ru.tigran.cardcollector.database.entity;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
public class User {

    @Id
    @Column(name = "id") private Long Id;
    @Column(name = "chat_id") private Long ChatId;
    @Column(name = "username") private String Username;
    @Column(name = "is_blocked") private Boolean IsBlocked;
    @Column(name = "privilege_level") private Integer PrivilegeLevel;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getChatId() {
        return ChatId;
    }

    public void setChatId(Long chatId) {
        ChatId = chatId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public Boolean getBlocked() {
        return IsBlocked;
    }

    public void setBlocked(Boolean blocked) {
        IsBlocked = blocked;
    }

    public Integer getPrivilegeLevel() {
        return PrivilegeLevel;
    }

    public void setPrivilegeLevel(Integer privilegeLevel) {
        PrivilegeLevel = privilegeLevel;
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

}
