package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService extends BaseService<Cliente,Long>{

    Page<Cliente> searchClienteApellido(String apellido, Pageable pageable) throws Exception;

    Page<Cliente> searchClienteNombre(String nombre, Pageable pageable) throws Exception;

}
