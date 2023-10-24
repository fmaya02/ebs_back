package com.elbuensabor.elbuensabor.mappers;

import com.elbuensabor.elbuensabor.dto.DTOCliente;
import com.elbuensabor.elbuensabor.entities.Persona;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClienteFromDto(DTOCliente dtoCliente, @MappingTarget Persona persona);
}
