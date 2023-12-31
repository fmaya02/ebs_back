package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dtos.DTOArticulosMasVendidos;
import com.elbuensabor.elbuensabor.entities.Articulo;
import com.elbuensabor.elbuensabor.repositories.ArticuloRepository;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

import java.util.Date;

@Service
public class ArticuloServiceImpl extends BaseServiceImpl<Articulo, Long> implements ArticuloService {
    @Autowired
    protected DetallePedidoServiceImpl detallePedidoServiceImpl;
    @Autowired
    protected ArticuloRepository articuloRepository;

    public ArticuloServiceImpl(ArticuloRepository articuloRepository, BaseRepository<Articulo, Long> baseRepository) {
        super(baseRepository);
        this.articuloRepository = articuloRepository;
    }

    @Override
    @Transactional
    public Page<Articulo> findByName(Pageable pageable, String name) throws Exception {
        try {
            Page<Articulo> entities = this.articuloRepository.findByName(name, pageable);
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Productos más pedidos, entre fechas
    @Override
    @Transactional
    public List<DTOArticulosMasVendidos> findMostSold(String fecha1, String fecha2) throws Exception{
        try {
            //convertir la fecha de String a Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date fechaInicio = dateFormat.parse(fecha1);
            Date fechaFin = dateFormat.parse(fecha2);
            /*Page<DTOArticulosMasVendidos> entities = this.detallePedidoServiceImpl.findMostSold(page, size, fechaInicio, fechaFin);*/
            return this.detallePedidoServiceImpl.findMostSold(fechaInicio, fechaFin);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
