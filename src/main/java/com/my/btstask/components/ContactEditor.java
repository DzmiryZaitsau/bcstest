package com.my.btstask.components;

import com.my.btstask.domain.Contact;
import com.my.btstask.domain.DTO.ContactDTO;
import com.my.btstask.mapper.AbstractContactMapper;
import com.my.btstask.mapper.AbstractContactMapperImpl;
import com.my.btstask.repositories.ContactRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class ContactEditor extends VerticalLayout implements KeyNotifier
{
    private ContactRepository contactRepository;
    private AbstractContactMapper abstractContactMapper = new AbstractContactMapperImpl();
    private Contact contact;
    private TextField telephone = new TextField("", "telephone");
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete");
    private HorizontalLayout buttons = new HorizontalLayout(save, cancel, delete);

    private Binder<Contact> binder = new Binder<>(Contact.class);
    @Setter
    private ChangeHandler changeHandler;

    public interface ChangeHandler
    {
        void onChange();
    }

    @Autowired
    public ContactEditor(ContactRepository contactRepository)
    {
        this.contactRepository = contactRepository;
        add(telephone, buttons);
        binder.bindInstanceFields(this);
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editContact(abstractContactMapper.toContactDTO(contact)));
        setVisible(false);
    }

    private void save()
    {
        contactRepository.save(contact);
        changeHandler.onChange();
    }

    private void delete()
    {
        contactRepository.delete(contact);
        changeHandler.onChange();
    }

    public void editContact(ContactDTO cont)
    {
        if (cont == null) {
            setVisible(false);
            return;
        }

        if (cont.getId() != null) {
            this.contact = contactRepository.findById(cont.getIdContact()).orElse(abstractContactMapper.toContact(cont));
        } else {
            this.contact = abstractContactMapper.toContact(cont);
        }

        binder.setBean(this.contact);

        setVisible(true);

        telephone.focus();

    }

}