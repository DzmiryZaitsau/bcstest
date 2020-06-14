package com.my.btstask.mapper;

import com.my.btstask.domain.DTO.PersonDTO;
import com.my.btstask.domain.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper
{
    PersonDTO toPersonDTO(Person person);

    Person toPerson(PersonDTO personDTO);

}
