package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Articulo;
import com.elbuensabor.elbuensabor.repositories.ArticuloRepository;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloServiceImpl extends BaseServiceImpl<Articulo, Long> implements ArticuloService{
    @Autowired
    private ArticuloRepository articuloRepository;
    public ArticuloServiceImpl(ArticuloRepository articuloRepository, BaseRepository<Articulo, Long> baseRepository){
        super(baseRepository);
        this.articuloRepository=articuloRepository;
    }
}
