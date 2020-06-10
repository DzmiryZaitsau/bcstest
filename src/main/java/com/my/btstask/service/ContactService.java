package com.my.btstask.service;

import com.my.btstask.domain.DTO.ContactDTO;
import com.my.btstask.mapper.AbstractContactMapper;
import com.my.btstask.mapper.AbstractContactMapperImpl;
import com.my.btstask.repositories.ContactRepository;
import com.vaadin.flow.component.grid.Grid;

public class ContactService {
    private AbstractContactMapper abstractContactMapper = new AbstractContactMapperImpl();
    public void showPersons(String name, ContactRepository repository, Grid<ContactDTO> gridContact) {
        if(name.isEmpty()) {
            gridContact.setItems(abstractContactMapper.toContactDTO(repository.findAll()));
            gridContact.removeColumnByKey("idContact");
        }
        else gridContact.setItems(abstractContactMapper.toContactDTO(repository.findByName(name)));

    }
    public void showPersonsById(Long id, ContactRepository repository, Grid<ContactDTO> gridContact) {
        if(id==null) {
            gridContact.setItems(abstractContactMapper.toContactDTO(repository.findAll()));
            gridContact.removeColumnByKey("idContact");
        }
        else gridContact.setItems(abstractContactMapper.toContactDTO(repository.findAll(id)));

    }
}
