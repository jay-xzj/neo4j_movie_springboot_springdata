package com.example.demo.repositories;


import com.example.demo.domain.Movie;
import com.example.demo.domain.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author mh
 * @since 24.07.12
 */
public interface MovieRepository extends Neo4jRepository<Movie,Long> {
    // the "0" parameter is a workaround for a bug in SDN
    Movie findByTitle(@Param("0") String title);

    Collection<Movie> findByTitleContaining(@Param("0") String title);

    @Query("MATCH (m:Movie)<-[:ACTED_IN]-(a:Person) RETURN m.title as movie, collect(a.name) as cast LIMIT $limit")
    List<Map<String, Object>> graph(@Param("limit") int limit);

    @Query("match (u:Person) where u.name = $name return u")
    Person getByName(@Param("name") String name);
}

