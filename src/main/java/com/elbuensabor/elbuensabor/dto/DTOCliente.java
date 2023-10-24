package com.elbuensabor.elbuensabor.dto;

import com.elbuensabor.elbuensabor.entities.Usuario;
import lombok.Data;

import java.util.Date;

@Data
public class DTOCliente {
    private Long id;

    private String nombre;

    private String apellido;

    private String telefono;

    private String email;

    private DTOUsuario dtoUsuario;

    private Date fechaAlta;

    private Date fechaModificacion;

    private Date fechaBaja;

}
