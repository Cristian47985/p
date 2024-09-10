package com.example.cqrs.reader.mappers;

import com.example.cqrs.reader.domain.PersonaReader;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MapperPersonaReader {
    PersonaReader personaPersisteceToPersonaDomain(com.example.cqrs.reader.persistence.PersonaReader persona);
    com.example.cqrs.reader.persistence.PersonaReader personaDomainToPersonaPersistece(PersonaReader persona);

    //List

    List<PersonaReader> personaPersisteceToPersonaDomainList(List<com.example.cqrs.reader.persistence.PersonaReader> persona);
    List<com.example.cqrs.reader.persistence.PersonaReader> personaDomainToPersonaPersisteceList(List<PersonaReader> persona);

}
