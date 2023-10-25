package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.UnidadMedida;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnidadMedidaServiceImpl extends BaseServiceImpl<UnidadMedida, Long> implements UnidadMedidaService {
    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    public UnidadMedidaServiceImpl(BaseRepository<UnidadMedida, Long> baseRepository, UnidadMedidaRepository unidadMedidaRepository) {
        super(baseRepository);
        this.unidadMedidaRepository = unidadMedidaRepository;
    }
}
