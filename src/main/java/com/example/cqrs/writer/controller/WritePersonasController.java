package com.example.cqrs.writer.controller;

import com.example.cqrs.writer.domain.Persona;
import com.example.cqrs.writer.mappers.MapperPersona;
import com.example.cqrs.writer.services.WritePersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personasWrite") // Define una ruta base para el controlador
public class WritePersonasController {

    @Autowired
    private WritePersonasService writePersonasService;

    @Autowired
    private MapperPersona mapperPersona;


    @PostMapping
    public ResponseEntity<Void> addPersona(@RequestBody Persona persona) {
        com.example.cqrs.writer.persistence.Persona p = mapperPersona.personaDomainToPersonaPersistece(persona);
        writePersonasService.addPersona(p);
        return ResponseEntity.ok().build(); // Retorna una respuesta 200 OK
    }

}
