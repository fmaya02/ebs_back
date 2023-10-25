package com.elbuensabor.elbuensabor.dto;

import com.elbuensabor.elbuensabor.enums.EstadoPersona;
import com.elbuensabor.elbuensabor.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DTOEmpleado {
    private String nombre;

    private String apellido;

    private String telefono;

    private String email;

    private EstadoPersona estadoPersona;

    private Rol rol;
}
