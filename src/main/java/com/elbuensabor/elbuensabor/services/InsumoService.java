package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Insumo;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InsumoService extends BaseService<Insumo, Long> {

    public Page<Insumo> findLowStock(Pageable pageable) throws Exception;
}
