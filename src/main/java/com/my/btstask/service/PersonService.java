package com.my.btstask.service;

import com.my.btstask.domain.Person;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonService
{
    List<Person> findByName(@Param("name") String name);

    Optional<Person> findById(long id);

    List<Person> findAll();

    void save(Person person);

    void delete(Person person);
}
