package com.example.cqrs.sync;

import com.example.cqrs.reader.persistence.PersonaReader;
import com.example.cqrs.reader.repositories.PostgresqlRepositoryReader;
import com.example.cqrs.writer.persistence.Persona;
import com.example.cqrs.writer.repositories.PostgresqlRepositoryWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SycnService {
    @Autowired
    PostgresqlRepositoryReader repositoryReader;
    @Autowired
    PostgresqlRepositoryWrite repositoryWrite;

    @Transactional
    public void sycn(){
        // Obtener todos los datos de la base de datos de escritura
        List<Persona> writeData = repositoryWrite.findAll();

        // Convertir los datos de escritura a datos de lectura
        List<PersonaReader> readData = writeData.stream()
                .map(persona -> new PersonaReader(persona.getId(), persona.getNombre(), persona.getEdad()))
                .toList();

        // Guardar los datos en la base de datos de lectura
        repositoryReader.saveAll(readData);
    }
}
