package com.my.btstask.mapper;

import com.my.btstask.domain.Contact;
import com.my.btstask.domain.DTO.PersonDTO;
import com.my.btstask.domain.Person;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonMapperTest
{
    private PersonMapper mapper
            = Mappers.getMapper(PersonMapper.class);
    List<Contact> list = new ArrayList<>();

    @Test
    public void givenToPersonDTO()
    {
        Person person = new Person();
        person.setId(23);
        person.setName("Jack");
        Contact contact = new Contact();
        contact.setId((long)1);
        contact.setTelephone("+777");
        contact.setPerson(person);
        list.add(contact);
        person.setContacts(list);
        PersonDTO personDTO = mapper.toPersonDTO(person);

        assertEquals(person.getId(), personDTO.getId());
        assertEquals(person.getName(), personDTO.getName());
        assertEquals(person.getContacts().get(0).getId(), personDTO.getContacts().get(0).getId());
        assertEquals(person.getContacts().get(0).getTelephone(), personDTO.getContacts().get(0).getTelephone());

    }

    @Test
    public void givenToPerson() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(1);
        personDTO.setName("Pol");
        list.clear();
        Contact contact = new Contact();
        contact.setId((long)1);
        contact.setTelephone("+777");
        contact.setPerson(new Person(1, "Pol"));
        list.add(contact);
        personDTO.setContacts(list);
        Person person = mapper.toPerson(personDTO);

        assertEquals(personDTO.getId(), person.getId());
        assertEquals(personDTO.getName(), person.getName());
        assertEquals(personDTO.getContacts().get(0).getId(), person.getContacts().get(0).getId());
        assertEquals(personDTO.getContacts().get(0).getTelephone(), person.getContacts().get(0).getTelephone());

    }
}
