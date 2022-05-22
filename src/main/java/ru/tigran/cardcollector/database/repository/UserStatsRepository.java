package ru.tigran.cardcollector.database.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.UserStats;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserStatsRepository extends JpaRepository<UserStats, Long > {

    @Query("select us " +
            "from UserStats us " +
            "where us.earnedExp > 0 " +
            "order by us.earnedExp desc, us.lastExpAccrual")
    List<UserStats> findTopByExp(Pageable pageable);

    @Query("select us " +
            "from UserStats us " +
            "where us.earned4TierStickers > 0 " +
            "order by us.earned4TierStickers desc, us.last4TierStickerAccrual")
    List<UserStats> findTopByTier4Stickers(Pageable pageable);

    @Query("select us " +
            "from UserStats us " +
            "where us.rouletteGames > 0 " +
            "order by us.rouletteGames desc, us.lastRouletteGame")
    List<UserStats> findTopByRouletteGames(Pageable pageable);

    @Query("select us " +
            "from UserStats us " +
            "where us.ladderGames > 0 " +
            "order by us.ladderGames desc, us.lastLadderGame")
    List<UserStats> findTopByLadderGames(Pageable pageable);

    @Query("select us " +
            "from UserStats us " +
            "where us.puzzleGames > 0 " +
            "order by us.puzzleGames desc, us.lastPuzzleGame")
    List<UserStats> findTopByPuzzleGames(Pageable pageable);

    @Query("select us " +
            "from UserStats us " +
            "where us.giftsReceived > 0 " +
            "order by us.giftsReceived desc, us.lastReceivedGift")
    List<UserStats> findTopByGiftsReceived(Pageable pageable);

    @Query("select us " +
            "from UserStats us " +
            "where us.friendsInvited > 0 " +
            "order by us.friendsInvited desc, us.lastInvitedFriend")
    List<UserStats> findTopByFriendsInvited(Pageable pageable);

    @Query("select count(us) from UserStats us where us.earnedExp > ?1 or us.earnedExp = ?1 and us.lastExpAccrual < ?2")
    Integer getExpPosition(Long earnedExp, LocalDateTime lastExpAccrual);

    @Query("select count(us) from UserStats us where us.earned4TierStickers > ?1 or us.earned4TierStickers = ?1 and us.last4TierStickerAccrual < ?2")
    Integer getTier4Position(Integer earned4TierStickers, LocalDateTime last4TierStickerAccrual);

    @Query("select count(us) from UserStats us where us.rouletteGames > ?1 or us.rouletteGames = ?1 and us.lastRouletteGame < ?2")
    Integer getRoulettePosition(Integer rouletteGames, LocalDateTime lastRouletteGame);

    @Query("select count(us) from UserStats us where us.ladderGames > ?1 or us.ladderGames = ?1 and us.lastLadderGame < ?2")
    Integer getLadderPosition(Integer ladderGames, LocalDateTime lastLadderGame);

    @Query("select count(us) from UserStats us where us.puzzleGames > ?1 or us.puzzleGames = ?1 and us.lastPuzzleGame < ?2")
    Integer getPuzzlePosition(Integer puzzleGames, LocalDateTime lastPuzzleGame);

    @Query("select count(us) from UserStats us where us.giftsReceived > ?1 or us.giftsReceived = ?1 and us.lastReceivedGift < ?2")
    Integer getGiftPosition(Integer giftsReceived, LocalDateTime lastReceivedGift);

    @Query("select count(us) from UserStats us where us.friendsInvited > ?1 or us.friendsInvited = ?1 and us.lastInvitedFriend < ?2")
    Integer getInvitePosition(Integer friendsInvited, LocalDateTime lastInvitedFriend);
}
