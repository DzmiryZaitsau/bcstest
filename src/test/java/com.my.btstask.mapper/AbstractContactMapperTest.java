package com.my.btstask.mapper;

import com.my.btstask.domain.Contact;
import com.my.btstask.domain.DTO.ContactDTO;
import com.my.btstask.domain.Person;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AbstractContactMapperTest
{
    private AbstractContactMapper mapper
            = Mappers.getMapper(AbstractContactMapper.class);

    @Test
    public void givenToContactDTO()
    {
        Contact contact = new Contact();
        contact.setId((long) 1);
        contact.setTelephone("+5555");
        contact.setPerson(new Person(1, "Jhon"));
        ContactDTO contactDTO = mapper.toContactDTO(contact);

        assertEquals(contact.getId(), contactDTO.getIdContact());
        assertEquals(contact.getTelephone(), contactDTO.getTelephone());
        assertEquals(contact.getPerson().getId(), contactDTO.getId());
        assertEquals(contact.getPerson().getName(), contactDTO.getName());
    }

    @Test
    public void givenToContact()
    {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId((long) 1);
        contactDTO.setTelephone("+5555");
        contactDTO.setId((long) 1);
        contactDTO.setName("Jhon");
        Contact contact = mapper.toContact(contactDTO);

        assertEquals(contactDTO.getIdContact(), contact.getId());
        assertEquals(contactDTO.getTelephone(), contact.getTelephone());
        assertEquals(contactDTO.getId(), contact.getPerson().getId());
        assertEquals(contactDTO.getName(), contact.getPerson().getName());
    }
}
