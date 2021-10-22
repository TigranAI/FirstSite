package ru.tigran.cardcollector.database.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.SessionTokens;

@Repository
public interface SessionTokenRepository extends CrudRepository<SessionTokens, Long> {

    @Query(value = "SELECT UserId FROM session_tokens WHERE Token = ?1")
    Long getIdByToken(String token);

}
