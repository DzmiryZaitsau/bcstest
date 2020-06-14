package com.my.btstask.domain.DTO;


import com.my.btstask.domain.Contact;

import java.util.List;

public class PersonDTO
{
    private long id;
    private String name;
    private List<Contact> contacts;

    public long getId()
    {
        return id;
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public List<Contact> getContacts()
    {
        return contacts;
    }

    public void setContacts(final List<Contact> contacts)
    {
        this.contacts = contacts;
    }
}
