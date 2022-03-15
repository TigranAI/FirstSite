package ru.tigran.cardcollector.database.entity;

import ru.tigran.cardcollector.database.converters.SettingsConverter;
import ru.tigran.cardcollector.enums.Settings;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "user_settings")
public class UserSettings {
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Column(columnDefinition = "longtext")
    @Convert(converter = SettingsConverter.class)
    private Map<Settings, Boolean> settings;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Settings, Boolean> getSettings() {
        return settings;
    }

    public void setSettings(Map<Settings, Boolean> settings) {
        this.settings = settings;
    }
}
