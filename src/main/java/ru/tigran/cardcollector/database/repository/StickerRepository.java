package ru.tigran.cardcollector.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.Pack;
import ru.tigran.cardcollector.database.entity.Sticker;

import java.util.List;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, Long> {
    List<Sticker> findAllByPack(Pack pack);

    @Query("select sticker " +
            "from Sticker sticker " +
            "where sticker.cacheFilePath is null")
    List<Sticker> findAllUncached();

    @Query("select sticker " +
            "from Sticker sticker " +
            "where sticker.forSaleFileId is not null and sticker.cacheForSaleFilePath is null")
    List<Sticker> findAllForSaleUncached();
}
