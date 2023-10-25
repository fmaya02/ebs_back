package com.elbuensabor.elbuensabor.services;
import com.elbuensabor.elbuensabor.entities.DetallePedido;
import com.elbuensabor.elbuensabor.entities.Factura;
import com.elbuensabor.elbuensabor.entities.NotaCredito;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.NotaCreditoRepository;
import jakarta.persistence.GenerationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class NotaCreditoServiceImpl extends BaseServiceImpl<NotaCredito,Long> implements NotaCreditoService {
    @Autowired
    NotaCreditoRepository notaCreditoRepository;

    public NotaCreditoServiceImpl(BaseRepository<NotaCredito, Long> baseRepository, NotaCreditoRepository notaCreditoRepository) {
        super(baseRepository);
        this.notaCreditoRepository = notaCreditoRepository;
    }

    @Override
    public NotaCredito createNotaCredito(Factura factura,int nro2) throws Exception {
        try {
            NotaCredito newNotaCredito = NotaCredito.builder()
                    .factura(factura)
                    .fechaComprobante(new Date())
                    .nro(nro2)
                    .total(factura.getPedido().getTotal())
                    .build();
            notaCreditoRepository.save(newNotaCredito);
            return newNotaCredito;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}