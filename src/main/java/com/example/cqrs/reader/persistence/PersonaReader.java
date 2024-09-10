package com.example.cqrs.reader.persistence;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "persona", schema = "public")
public class PersonaReader {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;
    String nombre;
    Integer edad;

}
