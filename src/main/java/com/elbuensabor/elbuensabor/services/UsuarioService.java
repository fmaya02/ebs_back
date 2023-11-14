package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dtos.DTOLogin;
import com.elbuensabor.elbuensabor.dtos.DTORegistro;
import com.elbuensabor.elbuensabor.dtos.DTOToken;
import com.elbuensabor.elbuensabor.entities.Persona;
import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.enums.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService extends BaseService<Usuario, Long> {

    public DTOToken signIn (DTOLogin dtoLogin) throws Exception;
    public Usuario createUsuario(DTORegistro dtoRegistro, List<Persona> mailcheck) throws Exception;

    public boolean checkPassword(String username, String password) throws Exception;

    public boolean firstTimeEmpleado(Usuario usr) throws Exception;
}
