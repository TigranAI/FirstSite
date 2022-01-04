package ru.tigran.cardcollector.database.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.UserSticker;

import java.util.ArrayList;

@Repository
public interface UserStickerRepository extends CrudRepository<UserSticker, Long> {
    @Query(value = "select * from user_to_stickers_relations us where us.user_id = ?1", nativeQuery = true)
    ArrayList<UserSticker> findAllByUserId(long userId);
}
