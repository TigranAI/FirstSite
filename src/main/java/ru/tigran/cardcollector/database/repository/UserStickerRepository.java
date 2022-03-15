package ru.tigran.cardcollector.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.User;
import ru.tigran.cardcollector.database.entity.UserSticker;

import java.util.List;

@Repository
public interface UserStickerRepository extends JpaRepository<UserSticker, Long> {
    List<UserSticker> findAllByUser(User user);
}
