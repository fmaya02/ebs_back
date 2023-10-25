package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dtos.DTOArticulosBajoStock;
import com.elbuensabor.elbuensabor.entities.Insumo;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InsumoService extends BaseService<Insumo, Long> {

    public List<DTOArticulosBajoStock> findLowStock(Pageable pageable) throws Exception;
}
