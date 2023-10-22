package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dtos.DTOArticulosMasVendidos;
import com.elbuensabor.elbuensabor.entities.Articulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ArticuloService extends BaseService<Articulo, Long>{

    public Page<Articulo> findByName(Pageable pageable, String name) throws Exception;

    public Page<DTOArticulosMasVendidos> findMostSold(Date fecha1, Date fecha2, Pageable pageable) throws Exception;

}
