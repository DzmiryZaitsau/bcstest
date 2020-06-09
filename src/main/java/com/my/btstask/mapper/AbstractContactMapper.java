package com.my.btstask.mapper;

import com.my.btstask.domain.Contact;
import com.my.btstask.domain.DTO.ContactDTO;
import com.my.btstask.domain.Person;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public abstract class AbstractContactMapper
{
    public ContactDTO toContactDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setIdContact(contact.getId());
        contactDTO.setId(contact.getPerson().getId());
        contactDTO.setTelephone(contact.getTelephone());
        contactDTO.setName(contact.getPerson().getName());
        return contactDTO;
    }

    public abstract List<ContactDTO> toContactDTO(Collection<Contact> contacts);

    public Contact toContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setId(contactDTO.getIdContact());
        contact.setTelephone(contactDTO.getTelephone());
        contact.setPerson(new Person(contactDTO.getId(), contactDTO.getName()));
        return contact;
    }

    public abstract List<Contact> toContact(Collection<ContactDTO> contacts);
}

