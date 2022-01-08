package ru.tigran.cardcollector.database.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.tigran.cardcollector.database.entity.UserLevel;

import java.util.List;

public interface UserLevelRepository extends CrudRepository<UserLevel, Long> {
    @Query(value = "select ul from user_level ul")
    List<UserLevel> findAll();
}
