package com.my.btstask.repositories;

import com.my.btstask.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long>
{
    @Query("from Person  p where p.name like concat(:name,'%') ")
    List<Person> findByName(@Param("name") String name);
}