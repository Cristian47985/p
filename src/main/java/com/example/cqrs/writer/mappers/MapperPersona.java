package com.example.cqrs.writer.mappers;

import com.example.cqrs.writer.domain.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MapperPersona {
    Persona personaPersisteceToPersonaDomain(com.example.cqrs.writer.persistence.Persona persona);
    com.example.cqrs.writer.persistence.Persona personaDomainToPersonaPersistece(Persona persona);

    //List

    List<Persona> personaPersisteceToPersonaDomainList(List<com.example.cqrs.writer.persistence.Persona> persona);
    List<com.example.cqrs.writer.persistence.Persona> personaDomainToPersonaPersisteceList(List<Persona> persona);

}
