package com.elbuensabor.elbuensabor.services;

import java.util.List;
import com.elbuensabor.elbuensabor.entities.Domicilio;
public interface DomicilioService extends BaseService<Domicilio, Long>{
    List<Domicilio> getAllDomicilios();
    Domicilio getDomicilioById(Long id);
    Domicilio createDomicilio(Domicilio domicilio);
    Domicilio updateDomicilio(Long id, Domicilio domicilio);
    boolean deleteDomicilio(Long id);
}
