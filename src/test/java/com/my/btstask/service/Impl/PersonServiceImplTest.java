package com.my.btstask.service.Impl;

import com.my.btstask.BtstaskApplication;
import com.my.btstask.domain.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SqlGroup({
        @Sql("/persons-table.sql"),
        @Sql("/persons-data.sql"),

})

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BtstaskApplication.class)
class PersonServiceImplTest
{
    @Autowired
    PersonServiceImpl personServiceImpl;

    @Test
    void findById()
    {
        assertEquals(11, personServiceImpl.findById(11).get().getId());
    }

    @Test
    void findAll()
    {
        assertEquals(4, personServiceImpl.findAll().size());
    }

    @Test
    void save()
    {
        personServiceImpl.save(new Person(777, "Test_Person"));
        assertEquals(1, personServiceImpl.findById(1).get().getId());
    }

    @Test
    void delete()
    {
        personServiceImpl.delete(personServiceImpl.findById(11).get());
        assertEquals(3, personServiceImpl.findAll().size());
    }
}