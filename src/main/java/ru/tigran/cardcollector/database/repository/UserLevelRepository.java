package ru.tigran.cardcollector.database.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tigran.cardcollector.database.entity.UserLevel;

public interface UserLevelRepository extends CrudRepository<UserLevel, Long> {
}
