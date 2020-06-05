package com.my.btstask.view;

import com.my.btstask.domain.Person;
import com.my.btstask.repositories.PersonRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainPage extends VerticalLayout
{
    private final PersonRepository personRepository;

    private Grid<Person> grid = new Grid<>(Person.class);

    public MainPage(final PersonRepository personRepository)
    {
        this.personRepository = personRepository;
        add(grid);
        grid.setItems(personRepository.findAll());
    }
}
