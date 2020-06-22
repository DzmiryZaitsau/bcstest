package com.my.btstask.service;

import com.my.btstask.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService
{
    Optional<Person> findById(long id);

    List<Person> findAll();

    void save(Person person);

    void delete(Person person);
}
