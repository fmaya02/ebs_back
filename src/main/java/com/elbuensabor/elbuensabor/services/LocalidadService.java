package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Localidad;
import java.util.List;

public interface LocalidadService {
    List<Localidad> getAllLocalidades();
    Localidad getLocalidadById(Long id);
    Localidad createLocalidad(Localidad localidad);
    Localidad updateLocalidad(Long id, Localidad localidad);
    boolean deleteLocalidad(Long id);
}