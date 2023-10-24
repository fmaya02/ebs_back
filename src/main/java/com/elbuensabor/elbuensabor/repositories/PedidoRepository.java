package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Pedido;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface PedidoRepository extends BaseRepository<Pedido, Long> {
    List<Pedido> findAllByOrderByFechaPedidoDesc();
    List<Pedido> findAllByOrderByCantidadDesc();
    List<Pedido> findAllByOrderByTotalDesc();
    List<Pedido> findByFechaPedidoBetween(Date fechaInicio, Date fechaFin);
}