package com.my.btstask.components;

import com.my.btstask.domain.Person;
import com.my.btstask.repositories.PersonRepository;
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
public class PersonEditor extends VerticalLayout implements KeyNotifier
{
    private PersonRepository personRepository;
    private Person person;
    private TextField name = new TextField("", "Name");
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete");
    private HorizontalLayout buttons = new HorizontalLayout(save, cancel, delete);

    private Binder<Person> binder = new Binder<>(Person.class);
    @Setter
    private ChangeHandler changeHandler;

    public interface ChangeHandler
    {
        void onChange();
    }

    @Autowired
    public PersonEditor(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
        add(name, buttons);
        binder.bindInstanceFields(this);
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editPerson(person));
        setVisible(false);
    }

    private void save()
    {
        personRepository.save(person);
        changeHandler.onChange();
    }

    private void delete()
    {
        personRepository.delete(person);
        changeHandler.onChange();
    }

    public void editPerson(Person pers)
    {
        if (pers == null) {
            setVisible(false);
            return;
        }

        if (pers.getId() != 0) {
            this.person = personRepository.findById(pers.getId()).orElse(pers);
        } else {
            this.person = pers;
        }

        binder.setBean(this.person);

        setVisible(true);

        name.focus();
    }

}
