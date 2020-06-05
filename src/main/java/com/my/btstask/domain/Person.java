package com.my.btstask.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "persons")
@Data
public class Person {
    @Id
    @GeneratedValue
    private long id;
    private String name;
}
