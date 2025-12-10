package com.example.personapi;

import com.example.personapi.entity.Personne;
import com.example.personapi.repository.PersonneRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class PersonApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonApiApplication.class, args);
    }

    @Bean
    CommandLineRunner loadData(PersonneRepository repository, ObjectMapper mapper, ResourceLoader resourceLoader) {
        return args -> {
            if (repository.count() > 0) {
                return;
            }
            Resource resource = resourceLoader.getResource("classpath:personnes.json");
            if (!resource.exists()) {
                throw new IllegalStateException("Resource personnes.json introuvable");
            }
            try (InputStream is = resource.getInputStream()) {
                List<Personne> personnes = mapper.readValue(is, new TypeReference<List<Personne>>() {});
                repository.saveAll(personnes);
            }
        };
    }
}
