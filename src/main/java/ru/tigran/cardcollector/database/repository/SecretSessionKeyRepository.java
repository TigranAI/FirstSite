package ru.tigran.cardcollector.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.SecretSessionKey;

@Repository
public interface SecretSessionKeyRepository extends CrudRepository<SecretSessionKey, String> {
}
