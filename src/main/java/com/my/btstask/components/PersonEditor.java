package com.my.btstask.components;

import com.my.btstask.domain.DTO.PersonDTO;
import com.my.btstask.domain.Person;
import com.my.btstask.mapper.PersonMapper;
import com.my.btstask.mapper.PersonMapperImpl;
import com.my.btstask.service.Impl.PersonServiceImpl;
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
    private PersonServiceImpl personServiceImpl;// = new PersonServiceImpl();
    private PersonMapper personMapper = new PersonMapperImpl();
    private Person person;
    private TextField name = new TextField("", "Name");
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete");
    private HorizontalLayout buttons = new HorizontalLayout(save, cancel, delete);

    private Binder<Person> binder = new Binder<>(Person.class);
    @Setter
    private ChangeHandler changeHandler = new ChangeHandler()
    {
        @Override
        public void onChange()
        {

        }
    };

    public interface ChangeHandler
    {
        void onChange();
    }

    @Autowired
    public PersonEditor(PersonServiceImpl personServiceImpl)
    {
        this.personServiceImpl = personServiceImpl;
        add(name, buttons);
        binder.bindInstanceFields(this);
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editPerson(personMapper.toPersonDTO(person)));
        setVisible(false);
    }

    private void save()
    {
        personServiceImpl.save(person);
        changeHandler.onChange();
    }

    private void delete()
    {
        personServiceImpl.delete(person);
        changeHandler.onChange();
    }

    public void editPerson(PersonDTO pers)
    {
        if (pers == null)
        {
            setVisible(false);
            return;
        }

        if (pers.getId() != 0)
        {
            this.person = personServiceImpl.findById(pers.getId()).orElse(personMapper.toPerson(pers));
        }
        else
        {
            this.person = personMapper.toPerson(pers);
        }

        binder.setBean(this.person);

        setVisible(true);

        name.focus();
    }

}
