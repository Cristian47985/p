package com.example.cqrs.writer.repositories;

import com.example.cqrs.writer.persistence.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresqlRepositoryWrite extends JpaRepository<Persona,Integer> {
}
