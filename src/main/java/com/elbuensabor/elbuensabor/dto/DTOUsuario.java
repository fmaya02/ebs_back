package com.elbuensabor.elbuensabor.dto;

import com.elbuensabor.elbuensabor.enums.Rol;
import lombok.Data;

import java.util.Date;

@Data
public class DTOUsuario {

    private String username;

    private Date fechaAlta;

    private Date fechaModificacion;

    private Date fechaBaja;

    private Rol rol;
}
