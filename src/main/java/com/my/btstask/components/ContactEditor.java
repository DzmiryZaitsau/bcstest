package com.my.btstask.components;

import com.my.btstask.domain.Contact;
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
        cancel.addClickListener(e -> editContact(contact));
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

    public void editContact(Contact cont)
    {
        if (cont == null) {
            setVisible(false);
            return;
        }

        if (cont.getId() != 0) {
            this.contact = contactRepository.findById(cont.getId()).orElse(cont);
        } else {
            this.contact = cont;
        }

        binder.setBean(this.contact);

        setVisible(true);

        telephone.focus();

    }

}