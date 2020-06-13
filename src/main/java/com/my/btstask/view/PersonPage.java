package com.my.btstask.view;

import com.my.btstask.components.ContactEditor;
import com.my.btstask.domain.Contact;
import com.my.btstask.domain.Person;
import com.my.btstask.mapper.AbstractContactMapper;
import com.my.btstask.mapper.AbstractContactMapperImpl;
import com.my.btstask.repositories.PersonRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("personPage")
public class PersonPage extends VerticalLayout {
    private final PersonRepository personRepository;
    private final AbstractContactMapper abstractContactMapper = new AbstractContactMapperImpl();

    private final ContactEditor contactEditor;
    private Grid<Person> gridPerson = new Grid<>(Person.class);

    public PersonPage(final PersonRepository personRepository, final ContactEditor contactEditor) {
        this.personRepository = personRepository;
        this.contactEditor = contactEditor;
        add(gridPerson, contactEditor);
        gridPerson.setItems(personRepository.findAll());
        gridPerson.removeColumnByKey("contacts");
        gridPerson.asSingleSelect().addValueChangeListener(e ->
                this.contactEditor.editContact(abstractContactMapper.toContactDTO(newContact(e.getValue()))));
    }
    private Contact newContact(Person person) {
        Contact contact = new Contact();
        contact.setId((long) 0);
        contact.setPerson(person);
        return contact;
    }
}
