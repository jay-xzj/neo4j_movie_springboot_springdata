package com.example.demo.domain;

import org.neo4j.ogm.annotation.*;
import org.springframework.stereotype.Indexed;

import java.util.Collection;

// tag::movie[]
@NodeEntity
public class Movie {
    @Id
    @GeneratedValue
    Long id;

    @Index
    String title;

    int released;
    String tagline;

    @Relationship(type = "ACTED_IN", direction = Relationship.INCOMING)
    Collection<Role> roles;

// end::movie[]

    public Movie() {
    }

    public String getTitle() {
        return title;
    }

    public int getReleased() {
        return released;
    }

    public String getTagline() {
        return tagline;
    }

    public Collection<Role> getRoles() {
        return roles;
    }
}
