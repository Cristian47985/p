package com.example.cqrs.reader.controller;

import com.example.cqrs.reader.domain.PersonaReader;
import com.example.cqrs.reader.mappers.MapperPersonaReader;
import com.example.cqrs.reader.services.ShowPersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personasReader") // Define una ruta base para el controlador
public class ShowPersonasController {

    @Autowired
    private ShowPersonasService showPersonasService;


    @Autowired
    private MapperPersonaReader mapperPersona;

    @GetMapping
    public List<PersonaReader> showPersonas() {
        return mapperPersona.personaPersisteceToPersonaDomainList(showPersonasService.showAll());
    }

}
