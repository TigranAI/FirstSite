package ru.tigran.cardcollector.database.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tigran.cardcollector.database.entity.Pack;

import java.util.ArrayList;

@Repository
public interface PackRepository extends CrudRepository<Pack, Integer> {

    @Override
    @Query(value = "select p from packs p where p.Id > 1")
    ArrayList<Pack> findAll();

    @Query(value = "select * from packs p where id <> 1 order by p.id desc limit ?1", nativeQuery = true)
    ArrayList<Pack> findLast(int count);

    @Query(value = "select distinct p.Author from packs p where p.Id <> 1 order by p.Author")
    ArrayList<String> findDistinctAuthors();
}
