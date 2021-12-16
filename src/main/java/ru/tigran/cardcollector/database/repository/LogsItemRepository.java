package ru.tigran.cardcollector.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.LogsItem;

import java.util.Date;

@Repository
public interface LogsItemRepository extends CrudRepository<LogsItem, Date> {
}
