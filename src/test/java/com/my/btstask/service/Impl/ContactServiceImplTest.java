package com.my.btstask.service.Impl;

import com.my.btstask.BtstaskApplication;
import com.my.btstask.domain.Contact;
import com.my.btstask.domain.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SqlGroup({
        @Sql("/persons-table.sql"),
        @Sql("/contacts-table.sql"),
        @Sql("/persons-data.sql"),
        @Sql("/contacts-data.sql"),

})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BtstaskApplication.class)
class ContactServiceImplTest
{
    @Autowired
    ContactServiceImpl contactServiceImpl;

    @Test
    void findByName()
    {
        assertEquals(1, contactServiceImpl.findByName("MikeTest").size());
    }

    @Test
    void findAll()
    {
        assertEquals(5, contactServiceImpl.findAll().size());
    }

    @Test
    void findAllId()
    {
        assertEquals(2, contactServiceImpl.findAll((long) 14).size());
    }

    @Test
    void save()
    {
        contactServiceImpl.save(new Contact(678, "+777", new Person(13, "Andreas")));
        assertEquals(6, contactServiceImpl.findAll().size());
    }

}