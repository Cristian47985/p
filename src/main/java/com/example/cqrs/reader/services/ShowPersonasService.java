package com.example.cqrs.reader.services;

import com.example.cqrs.reader.persistence.PersonaReader;
import com.example.cqrs.reader.repositories.PostgresqlRepositoryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowPersonasService {

    @Autowired
    PostgresqlRepositoryReader postgresqlRepository;

    public List<PersonaReader> showAll(){

        return postgresqlRepository.findAll();
    }
}
