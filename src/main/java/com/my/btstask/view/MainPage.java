package com.my.btstask.view;

import com.my.btstask.components.PersonEditor;
import com.my.btstask.domain.DTO.ContactDTO;
import com.my.btstask.domain.Person;
import com.my.btstask.mapper.AbstractContactMapper;
import com.my.btstask.mapper.AbstractContactMapperImpl;
import com.my.btstask.mapper.PersonMapper;
import com.my.btstask.mapper.PersonMapperImpl;
import com.my.btstask.repositories.ContactRepository;
import com.my.btstask.service.Impl.ContactServiceImpl;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route
public class MainPage extends VerticalLayout
{
    private final ContactRepository contactRepository;

    private Grid<ContactDTO> gridContact = new Grid<>(ContactDTO.class);
    private ContactServiceImpl contactServiceImpl;// = new ContactServiceImpl();
    private final AbstractContactMapper abstractContactMapper = new AbstractContactMapperImpl();
    private final PersonMapper personMapper = new PersonMapperImpl();
    private final TextField filter = new TextField("", "Filter by Name");
    private final TextField filterId = new TextField("", "Filter by Id");
    private RouterLink linkPerson = new RouterLink("Show All Person", PersonPage.class);
    private final Button addNewPerson = new Button("New person", VaadinIcon.PLUS.create());
    private final PersonEditor personEditor;
    private final HorizontalLayout toolbar = new HorizontalLayout(filter,filterId, linkPerson, addNewPerson);

    public MainPage( ContactRepository contactRepository, PersonEditor personEditor, ContactServiceImpl contactServiceImpl)
    {
        this.contactRepository = contactRepository;
        this.personEditor = personEditor;
        this.contactServiceImpl = contactServiceImpl;
        add(toolbar, gridContact, personEditor);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> contactServiceImpl.showPersons(e.getValue(), gridContact));
        filterId.setValueChangeMode(ValueChangeMode.EAGER);
        filterId.addValueChangeListener(e -> contactServiceImpl.showPersonsById(Long.parseLong(e.getValue()), gridContact));

        gridContact.asSingleSelect().addValueChangeListener(e ->
            this.personEditor.editPerson(personMapper.toPersonDTO
                    (abstractContactMapper.toContact(e.getValue()).getPerson())));
        addNewPerson.addClickListener(e -> this.personEditor.editPerson(personMapper.toPersonDTO(new Person())));

        contactServiceImpl.showPersons("", gridContact);


    }
}
