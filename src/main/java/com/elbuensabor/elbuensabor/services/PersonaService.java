package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dtos.DTOCliente;
import com.elbuensabor.elbuensabor.dtos.DTOClienteRanking;
import com.elbuensabor.elbuensabor.dtos.DTOEmpleado;
import com.elbuensabor.elbuensabor.entities.Persona;
import com.elbuensabor.elbuensabor.enums.Rol;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonaService extends BaseService<Persona,Long>{

    Persona signUp (Persona persona, Rol rol, String pswd1, String pswd2) throws Exception;

    List<Persona> findPersonaByEmail (String email) throws Exception;



    List<DTOCliente> getAllClientes () throws Exception;

    List<DTOEmpleado> getAllEmpleados () throws Exception;

    DTOCliente getCliente (Long id) throws Exception;

    DTOEmpleado getEmpleado (Long id) throws Exception;

    DTOCliente patchCliente (DTOCliente dtoCliente, Long id) throws Exception;

    DTOEmpleado patchEmpleado (DTOEmpleado dtoEmpleado, Long id) throws Exception;

    List<DTOClienteRanking> getRankingClientes () throws Exception;

}
