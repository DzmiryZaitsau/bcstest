package com.my.btstask.repositories;

import com.my.btstask.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long>
{
}
