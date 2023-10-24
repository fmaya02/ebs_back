package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.dtos.DTOArticulosMasVendidos;
import com.elbuensabor.elbuensabor.entities.DetallePedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido, Long> {

    @Query(name = "productosMasVendidos",
            value = "SELECT a.DENOMINACION AS denominacionArticulo, a.ID AS articuloId, SUM(d.CANTIDAD) AS cantidad , a.URL_IMAGEN AS urlImagen, r.DENOMINACION AS denominacionRubro " +
                    "FROM DETALLE_PEDIDO d " +
                    "INNER JOIN ARTICULO a ON d.ARTICULO_ID = a.ID " +
                    "INNER JOIN PEDIDO p ON d.PEDIDO_ID = p.ID " +
                    "INNER JOIN RUBRO_ARTICULO r ON r.ID = a.RUBRO_ARTICULO_ID " +
                    "WHERE :filtro1 < p.FECHA_PEDIDO AND p.FECHA_PEDIDO < :filtro2 " +
                    "GROUP BY a.ID ORDER BY RUBRO_ARTICULO_ID",
            nativeQuery=true)
    public Page<DTOArticulosMasVendidos> findMostSold(@Param("filtro1") Date filtro1, @Param("filtro2") Date filtro2, Pageable pageable);
}
