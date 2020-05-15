package com.example.demo.domain;

import org.neo4j.ogm.annotation.*;

import java.util.Collection;

@NodeEntity
public class Person {
    @Id
    @GeneratedValue
    Long id;

    @Index
    private String name;
    private int born;

    @Relationship(type = "ACTED_IN")
    Collection<Movie> movies;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public int getBorn() {
        return born;
    }

    public Collection<Movie> getMovies() {
        return movies;
    }
}
