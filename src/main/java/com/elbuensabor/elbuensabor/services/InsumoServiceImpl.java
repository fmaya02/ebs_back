package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Insumo;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsumoServiceImpl extends BaseServiceImpl<Insumo, Long> implements InsumoService{

    @Autowired
    private InsumoRepository insumoRepository;

    public InsumoServiceImpl(BaseRepository<Insumo, Long> baseRepository, InsumoRepository insumoRepository) {
        super(baseRepository);
        this.insumoRepository=insumoRepository;
    }
}
