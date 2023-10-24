package com.elbuensabor.elbuensabor.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.elbuensabor.elbuensabor.repositories.LocalidadRepository;
import com.elbuensabor.elbuensabor.entities.Localidad;

@Service
public class LocalidadServiceImpl implements LocalidadService {

    private final LocalidadRepository localidadRepository;

    @Autowired
    public LocalidadServiceImpl(LocalidadRepository localidadRepository) {
        this.localidadRepository = localidadRepository;
    }

    @Override
    public List<Localidad> getAllLocalidades() {
        return localidadRepository.findAll();
    }

    @Override
    public Localidad getLocalidadById(Long id) {
        return localidadRepository.findById(id).orElse(null);
    }

    @Override
    public Localidad createLocalidad(Localidad localidad) {
        return localidadRepository.save(localidad);
    }

    @Override
    public Localidad updateLocalidad(Long id, Localidad localidad) {
        if (localidadRepository.existsById(id)) {
            localidad.setId(id);
            return localidadRepository.save(localidad);
        }
        return null;
    }

    @Override
    public boolean deleteLocalidad(Long id) {
        if (localidadRepository.existsById(id)) {
            localidadRepository.deleteById(id);
            return true;
        }
        return false;
    }
}