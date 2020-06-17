package com.my.btstask.service.Impl;

import com.my.btstask.domain.Person;
import com.my.btstask.repositories.PersonRepository;
import com.my.btstask.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PersonServiceImpl implements PersonService
{
    @Autowired
    PersonRepository personRepository;
    @Override
    public List<Person> findByName(final String name)
    {
        return personRepository.findByName(name);
    }

    @Override
    public Optional<Person> findById(final long id)
    {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findAll()
    {
        return personRepository.findAll();
    }

    @Override
    public void save(final Person person)
    {
        personRepository.save(person);
    }

    @Override
    public void delete(final Person person)
    {
        personRepository.delete(person);
    }
}
