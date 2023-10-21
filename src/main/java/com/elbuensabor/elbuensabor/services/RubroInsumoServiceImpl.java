package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.RubroInsumo;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.RubroInsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RubroInsumoServiceImpl extends BaseServiceImpl<RubroInsumo, Long> implements RubroInsumoService{

    @Autowired
    private RubroInsumoRepository rubroInsumoRepository;

    public RubroInsumoServiceImpl(BaseRepository<RubroInsumo, Long> baseRepository, RubroInsumoRepository rubroInsumoRepository) {
        super(baseRepository);
        this.rubroInsumoRepository=rubroInsumoRepository;
    }
}
