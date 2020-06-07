package com.my.btstask.view;

import com.my.btstask.domain.Contact;
import com.my.btstask.repositories.ContactRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainPage extends VerticalLayout
{
    private final ContactRepository contactRepository;

    private Grid<Contact> gridContact = new Grid<>(Contact.class);

    public MainPage(final ContactRepository contactRepository)
    {
        this.contactRepository = contactRepository;
        add(gridContact);
        gridContact.setItems(contactRepository.findAll());
        gridContact.addColumn(contact -> contact.getPerson().getId());
        gridContact.addColumn(contact -> contact.getPerson().getName());
        gridContact.removeColumnByKey("id");
        gridContact.removeColumnByKey("person");
        gridContact.removeColumnByKey("telephone");
        gridContact.addColumn(Contact::getTelephone);

    }
}
