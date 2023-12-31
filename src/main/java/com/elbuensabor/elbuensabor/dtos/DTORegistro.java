package com.elbuensabor.elbuensabor.dtos;

import com.elbuensabor.elbuensabor.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DTORegistro {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String username;
    private String password;
    private Rol rol;
}
