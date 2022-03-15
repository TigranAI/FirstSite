package ru.tigran.cardcollector.database.entity;

import ru.tigran.cardcollector.database.converters.DailyTaskTypeConverter;
import ru.tigran.cardcollector.enums.DailyTaskType;

import javax.persistence.*;

@Entity
@Table(name = "daily_tasks")
public class DailyTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Convert(converter = DailyTaskTypeConverter.class)
    private DailyTaskType taskId;

    @Column(nullable = false)
    private Integer progress;

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

    public DailyTaskType getTaskId() {
        return taskId;
    }

    public void setTaskId(DailyTaskType taskId) {
        this.taskId = taskId;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}
