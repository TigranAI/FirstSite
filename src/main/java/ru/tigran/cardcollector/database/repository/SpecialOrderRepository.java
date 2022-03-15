package ru.tigran.cardcollector.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.SpecialOrder;

import java.util.List;

@Repository
public interface SpecialOrderRepository extends JpaRepository<SpecialOrder, Integer> {

    @Query("select specialOrder " +
            "from SpecialOrder specialOrder " +
            "where specialOrder.previewFileId is not null and specialOrder.cacheFilePath is null")
    List<SpecialOrder> findAllUncached();
}
