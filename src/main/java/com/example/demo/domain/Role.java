package com.example.demo.domain;


import org.neo4j.ogm.annotation.*;

import java.util.Collection;

@RelationshipEntity(type = "ACTED_IN")
public class Role {
    @Id
    @GeneratedValue
    Long id;
    Collection<String> roles;
    @StartNode
    Person person;
    @EndNode
    Movie movie;

    public Collection<String> getRoles() {
        return roles;
    }

    public Person getPerson() {
        return person;
    }

    public Movie getMovie() {
        return movie;
    }
}
