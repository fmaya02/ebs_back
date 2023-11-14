package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dtos.DTOArticulosMasVendidos;
import com.elbuensabor.elbuensabor.entities.DetallePedido;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.elbuensabor.elbuensabor.repositories.DetallePedidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DetallePedidoServiceImpl extends BaseServiceImpl<DetallePedido, Long> implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoServiceImpl(BaseRepository<DetallePedido, Long> baseRepository, DetallePedidoRepository detallePedidoRepository) {
        super(baseRepository);
        this.detallePedidoRepository = detallePedidoRepository;
    }
    @Override
    @Transactional
    public List<DTOArticulosMasVendidos> findMostSold(Date date1, Date date2) throws Exception{
        try {
            List<DTOArticulosMasVendidos> entities = this.detallePedidoRepository.findMostSold(date1, date2);
//            Pageable pageable = PageRequest.of(page, size);
//            Page<DTOArticulosMasVendidos> returnedPage = new PageImpl<>(entities, pageable, entities.size());
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
