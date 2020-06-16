package com.my.btstask.service;

import com.my.btstask.domain.Contact;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactService
{
    List<Contact> findByName(@Param("name") String name);

    List<Contact> findAll(@Param("id") Long id);

    List<Contact> findAll();

    void save(Contact contact);

    void delete(Contact contact);
}
