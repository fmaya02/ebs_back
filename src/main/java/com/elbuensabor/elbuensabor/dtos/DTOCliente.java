package com.elbuensabor.elbuensabor.dtos;

import com.elbuensabor.elbuensabor.enums.EstadoPersona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
