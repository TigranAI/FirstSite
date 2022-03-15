package ru.tigran.cardcollector.database.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.Pack;

import java.util.List;

@Repository
public interface PackRepository extends JpaRepository<Pack, Integer> {

    @Query("select pack " +
            "from Pack pack " +
            "where pack.id <> 1 " +
            "order by pack.id desc")
    List<Pack> findLast(Pageable pageable);

    @Query("select pack " +
            "from Pack pack " +
            "where pack.id <> 1")
    List<Pack> findAllSkipId1();

    @Query("select pack " +
            "from Pack pack " +
            "where pack.previewFileId is not null and pack.cacheFilePath is null")
    List<Pack> findAllUncached();
}
