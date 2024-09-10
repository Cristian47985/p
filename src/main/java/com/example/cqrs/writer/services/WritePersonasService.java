package com.example.cqrs.writer.services;

import com.example.cqrs.writer.persistence.Persona;
import com.example.cqrs.writer.repositories.PostgresqlRepositoryWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WritePersonasService {

    @Autowired
    PostgresqlRepositoryWrite postgresqlRepository;

    public void addPersona(Persona persona){
        postgresqlRepository.save(persona);
    }
}
