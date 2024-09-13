package com.example.cqrs.reader.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "persona", schema = "public")
public class PersonaReader {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;
    String nombre;
    Integer edad;

}
