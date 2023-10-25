package com.elbuensabor.elbuensabor.dto;

import com.elbuensabor.elbuensabor.enums.EstadoPersona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DTOCliente {

    private String nombre;

    private String apellido;

    private String telefono;

    private String email;

    private EstadoPersona estadoPersona;

}
