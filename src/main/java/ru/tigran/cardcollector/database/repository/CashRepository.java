package ru.tigran.cardcollector.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.Cash;
import ru.tigran.cardcollector.database.entity.User;

@Repository
public interface CashRepository extends CrudRepository<Cash, Long> {
}
