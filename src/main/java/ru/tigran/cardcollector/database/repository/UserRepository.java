package ru.tigran.cardcollector.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
