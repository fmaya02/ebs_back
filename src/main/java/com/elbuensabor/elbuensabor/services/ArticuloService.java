package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dtos.DTOArticulosMasVendidos;
import com.elbuensabor.elbuensabor.entities.Articulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticuloService extends BaseService<Articulo, Long>{

    public Page<Articulo> findByName(Pageable pageable, String name) throws Exception;

    public List<DTOArticulosMasVendidos> findMostSold(String fecha1, String fecha2) throws Exception;

}
