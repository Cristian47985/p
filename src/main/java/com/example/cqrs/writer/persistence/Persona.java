package com.example.cqrs.writer.persistence;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "persona", schema = "public")
public class Persona {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;
    String nombre;
    Integer edad;

}
