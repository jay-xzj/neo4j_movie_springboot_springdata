package com.example.demo;

import com.example.demo.domain.Person;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.services.MovieService;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages= Constants.BASE_PACKAGE)
@SpringBootApplication(scanBasePackages = Constants.BASE_PACKAGE)
@RestController("/")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Value("${spring.data.neo4j.uri}")
    private String databaseUrl;

    @Bean
    public SessionFactory sessionFactory() {
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
                .uri(databaseUrl)
                .build();
        return new SessionFactory(configuration, Constants.BASE_PACKAGE);
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/getByName/{name}")
    public void graph(@PathVariable String name) {
        Person byName = movieRepository.getByName(name);
        System.out.println(byName.getBorn());
    }

}
