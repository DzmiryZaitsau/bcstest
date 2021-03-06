package com.my.btstask.service.Impl;

import com.my.btstask.domain.Contact;
import com.my.btstask.domain.DTO.ContactDTO;
import com.my.btstask.mapper.AbstractContactMapper;
import com.my.btstask.mapper.AbstractContactMapperImpl;
import com.my.btstask.repositories.ContactRepository;
import com.my.btstask.service.ContactService;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactServiceImpl implements ContactService
{
    @Autowired
    ContactRepository contactRepository;
    private AbstractContactMapper abstractContactMapper = new AbstractContactMapperImpl();
    public void showPersons(String name, Grid<ContactDTO> gridContact) {
        if(name.isEmpty()) {
            gridContact.setItems(abstractContactMapper.toContactDTO(findAll()));
            gridContact.removeColumnByKey("idContact");
        }
        else gridContact.setItems(abstractContactMapper.toContactDTO(findByName(name)));

    }
    public void showPersonsById(Long id, Grid<ContactDTO> gridContact) {
        if(id==null) {
            gridContact.setItems(abstractContactMapper.toContactDTO(findAll()));
            gridContact.removeColumnByKey("idContact");
        }
        else gridContact.setItems(abstractContactMapper.toContactDTO(findAll(id)));

    }

    @Override
    public List<Contact> findByName(final String name)
    {
        return contactRepository.findByName(name);
    }

    @Override
    public List<Contact> findAll(final Long id)
    {
        return contactRepository.findAll(id);
    }

    @Override
    public List<Contact> findAll()
    {
        return contactRepository.findAll();
    }

    @Override
    public void save(final Contact contact)
    {
        contactRepository.save(contact);
    }

    @Override
    public void delete(final Contact contact)
    {
        contactRepository.delete(contact);
    }
}
