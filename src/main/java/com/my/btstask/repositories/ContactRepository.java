package com.my.btstask.repositories;

import com.my.btstask.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long>
{
    @Query("from Contact  c where c.person.name  like concat(:name,'%') ")
    List<Contact> findByName(@Param("name") String name);

    @Query("from Contact  c where c.person.id=:id")
    List<Contact> findAll(@Param("id") Long id);
}
