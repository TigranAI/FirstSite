package ru.tigran.cardcollector.database.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select user " +
            "from User user " +
            "where user.privilegeLevel < 6 " +
            "order by user.level.totalExp desc")
    List<User> findTopByExp(Pageable pageable);

    @Query("select user " +
            "from User user join UserSticker userSticker on user = userSticker.user and userSticker.sticker.tier = 4 " +
            "group by user having user.privilegeLevel < 6 " +
            "order by count(userSticker)")
    List<User> findTopByTier4Stickers(Pageable pageable);
}
