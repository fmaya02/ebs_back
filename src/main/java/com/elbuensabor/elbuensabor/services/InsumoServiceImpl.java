package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dtos.DTOArticulosBajoStock;
import com.elbuensabor.elbuensabor.entities.Insumo;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.InsumoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsumoServiceImpl extends BaseServiceImpl<Insumo, Long> implements InsumoService{

    @Autowired
    private InsumoRepository insumoRepository;

    public InsumoServiceImpl(BaseRepository<Insumo, Long> baseRepository, InsumoRepository insumoRepository) {
        super(baseRepository);
        this.insumoRepository=insumoRepository;
    }

    @Override
    @Transactional
    public List<DTOArticulosBajoStock> findLowStock(Pageable pageable) throws Exception {
        try {
            List<DTOArticulosBajoStock> entities = this.insumoRepository.findLowStock(pageable);
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
