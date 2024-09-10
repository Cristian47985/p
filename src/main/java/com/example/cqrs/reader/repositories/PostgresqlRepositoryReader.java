package com.example.cqrs.reader.repositories;

import com.example.cqrs.reader.persistence.PersonaReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresqlRepositoryReader extends JpaRepository<PersonaReader,Integer> {
}
