package ru.tigran.cardcollector.database.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.Pack;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public interface PackRepository extends CrudRepository<Pack, Integer> {

    @Override
    @Query(value = "select p from packs p where p.Id > 1")
    public ArrayList<Pack> findAll();
}
