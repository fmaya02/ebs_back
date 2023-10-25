package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.elbuensabor.elbuensabor.entities.Domicilio;
import com.elbuensabor.elbuensabor.repositories.DomicilioRepository;

@Service
public class DomicilioServiceImpl extends BaseServiceImpl<Domicilio, Long> implements DomicilioService {

    @Autowired
    private DomicilioRepository domicilioRepository;

    public DomicilioServiceImpl(BaseRepository<Domicilio, Long> baseRepository) {
        super(baseRepository);
        this.baseRepository=baseRepository;
    }

    @Override
    public List<Domicilio> getAllDomicilios() {
        return domicilioRepository.findAll();
    }

    @Override
    public Domicilio getDomicilioById(Long id) {
        return domicilioRepository.findById(id).orElse(null);
    }

    @Override
    public Domicilio createDomicilio(Domicilio domicilio) {
        return domicilioRepository.save(domicilio);
    }

    @Override
    public Domicilio updateDomicilio(Long id, Domicilio domicilio) {
        if (domicilioRepository.existsById(id)) {
            domicilio.setId(id);
            return domicilioRepository.save(domicilio);
        }
        return null;
    }

    @Override
    public boolean deleteDomicilio(Long id) {
        if (domicilioRepository.existsById(id)) {
            domicilioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}