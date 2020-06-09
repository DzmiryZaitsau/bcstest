package com.my.btstask.domain.DTO;

import lombok.Data;

@Data
public class ContactDTO
{
    private Long idContact;
    //Person's id
    private Long id;
    private String telephone;
    //Person's name
    private String name;

    public Long getIdContact()
    {
        return idContact;
    }

    public void setIdContact(final Long idContact)
    {
        this.idContact = idContact;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(final String telephone)
    {
        this.telephone = telephone;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
