package com.my.btstask.view;

import com.my.btstask.components.ContactEditor;
import com.my.btstask.domain.Contact;
import com.my.btstask.repositories.ContactRepository;
import com.my.btstask.service.ContactService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainPage extends VerticalLayout
{
    private final ContactRepository contactRepository;

    private Grid<Contact> gridContact = new Grid<>(Contact.class);
    private ContactService contactService = new ContactService();
    private final TextField filter = new TextField("", "Type to filter");;
    private final Button addNewPerson = new Button("Add new");
    private final ContactEditor contactEditor;
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewPerson);

    public MainPage(final ContactRepository contactRepository, final ContactEditor contactEditor)
    {
        this.contactRepository = contactRepository;
        this.contactEditor = contactEditor;
        add(toolbar, gridContact, contactEditor);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> contactService.showPersons(e.getValue(), contactRepository, gridContact));

        gridContact.asSingleSelect().addValueChangeListener(e -> {
            this.contactEditor.editContact(e.getValue());
                });
        contactService.showPersons("", contactRepository, gridContact);


    }
}
