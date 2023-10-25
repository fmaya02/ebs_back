package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.RubroArticulo;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.RubroArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RubroArticuloServiceImpl extends BaseServiceImpl<RubroArticulo, Long> implements RubroArticuloService {
@Autowired
    private RubroArticuloRepository rubroArticuloRepository;

    public RubroArticuloServiceImpl(BaseRepository<RubroArticulo, Long> baseRepository, RubroArticuloRepository rubroArticuloRepository) {
        super(baseRepository);
        this.rubroArticuloRepository = rubroArticuloRepository;
    }
}
