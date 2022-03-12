package ru.tigran.cardcollector.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.Sticker;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, String> {
    @Query(value = "select s from stickers s where ?1 = 1 or s.PackId = ?1 order by s.Tier")
    ArrayList<Sticker> findByPackId(int packId);

    @Query(value = "select s from stickers s where s.MD5Hash = ?1")
    Sticker findByHash(String md5hash);

    @Override
    ArrayList<Sticker> findAll();

    @Query(value = "select s from stickers s where s.Id in ?1")
    ArrayList<Sticker> findAllByStickersId(List<String> stickersId);

    @Query(value = "select distinct s.Emoji from stickers s")
    ArrayList<String> findDistinctEmojis();

    @Query(value = "select s.Id from stickers s where s.Tier = ?1")
    List<String> findAllIdByTier(Integer tier);
}
