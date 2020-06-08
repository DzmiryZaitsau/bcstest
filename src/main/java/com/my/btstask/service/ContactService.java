package com.my.btstask.service;

import com.my.btstask.domain.Contact;
import com.my.btstask.repositories.ContactRepository;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.data.jpa.repository.JpaRepository;

public class ContactService
{
    public void showPersons(String name, ContactRepository repository, Grid<Contact> gridContact) {
        if(name.isEmpty()) {
            gridContact.setItems(repository.findAll());
            gridContact.addColumn(contact -> contact.getPerson().getName());
            gridContact.removeColumnByKey("person");
            gridContact.removeColumnByKey("telephone");
            gridContact.addColumn(Contact::getTelephone);
        }
        else gridContact.setItems(repository.findByName(name));

    }
}
