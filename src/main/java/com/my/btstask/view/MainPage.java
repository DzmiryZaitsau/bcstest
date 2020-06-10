package com.my.btstask.view;

import com.my.btstask.components.PersonEditor;
import com.my.btstask.domain.DTO.ContactDTO;
import com.my.btstask.domain.Person;
import com.my.btstask.mapper.AbstractContactMapper;
import com.my.btstask.mapper.AbstractContactMapperImpl;
import com.my.btstask.repositories.ContactRepository;
import com.my.btstask.service.ContactService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainPage extends VerticalLayout
{
    private final ContactRepository contactRepository;

    private Grid<ContactDTO> gridContact = new Grid<>(ContactDTO.class);
    private ContactService contactService = new ContactService();
    private AbstractContactMapper abstractContactMapper = new AbstractContactMapperImpl();
    private final TextField filter = new TextField("", "Filter by Name");
    private final TextField filterId = new TextField("", "Filter by Id");
    private final TextField findPersonByName = new TextField("", "Filter by Id");
    private final Button addNewPerson = new Button("New person", VaadinIcon.PLUS.create());
    private final PersonEditor personEditor;
    private final HorizontalLayout toolbar = new HorizontalLayout(filter,filterId, addNewPerson);

    public MainPage( ContactRepository contactRepository, PersonEditor personEditor)
    {
        this.contactRepository = contactRepository;
        this.personEditor = personEditor;
        add(toolbar, gridContact, personEditor);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> contactService.showPersons(e.getValue(), contactRepository, gridContact));
        filterId.setValueChangeMode(ValueChangeMode.EAGER);
        filterId.addValueChangeListener(e -> contactService.showPersonsById(Long.parseLong(e.getValue()), contactRepository, gridContact));

        gridContact.asSingleSelect().addValueChangeListener(e ->
            this.personEditor.editPerson(abstractContactMapper.toContact(e.getValue()).getPerson())
                );
        addNewPerson.addClickListener(e -> this.personEditor.editPerson(new Person()));

        contactService.showPersons("", contactRepository, gridContact);


    }
}
