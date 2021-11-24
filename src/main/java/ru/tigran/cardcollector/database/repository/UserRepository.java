package ru.tigran.cardcollector.database.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value =
            "select * " +
            "from users as u " +
            "inner join " +
                "(select id, total_exp " +
                "from user_level) as l " +
            "on u.id = l.id " +
            "order by l.total_exp desc " +
            "limit ?1", nativeQuery = true)
    List<User> findTopByExp(int count);
}
