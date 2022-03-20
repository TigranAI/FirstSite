package ru.tigran.cardcollector.database.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select user " +
            "from User user " +
            "where user.privilegeLevel < 6 " +
            "order by user.level.totalExp desc")
    List<User> findTopByExp(Pageable pageable);

    @Query("select user " +
            "from User user " +
            "   join UserSticker userSticker on user = userSticker.user and user.privilegeLevel < 6 " +
            "   join Sticker sticker on userSticker.sticker = sticker and sticker.tier = 4 " +
            "group by user " +
            "order by sum(userSticker.count) desc")
    List<User> findTopByTier4Stickers(Pageable pageable);
}
