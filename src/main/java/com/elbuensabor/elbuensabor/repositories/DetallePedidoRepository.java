package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.dtos.DTOArticulosMasVendidos;
import com.elbuensabor.elbuensabor.entities.DetallePedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido, Long> {

    @Query("SELECT new com.elbuensabor.elbuensabor.dtos.DTOArticulosMasVendidos(" +
            "SUM(d.cantidad) AS cantidad, a.Id AS articuloId, a.denominacion AS denominacionArticulo, " +
            "a.urlImagen AS urlImagen, r.denominacion AS denominacionRubro) " +
            "FROM Pedido p " +
            "JOIN p.pedidoDetalles d " +
            "JOIN d.articulo a " +
            "JOIN a.rubroArticulo r " +
            "WHERE :filtro1 < p.fechaPedido AND p.fechaPedido < :filtro2 " +
            "GROUP BY a.Id "+
            "ORDER BY cantidad DESC")
    List<DTOArticulosMasVendidos> findMostSold(
            @Param("filtro1") Date filtro1,
            @Param("filtro2") Date filtro2
    );
}
