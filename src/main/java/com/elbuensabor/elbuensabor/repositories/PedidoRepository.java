package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Pedido;
import com.elbuensabor.elbuensabor.enums.EstadoPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {
    @Query(value="SELECT p FROM Pedido p WHERE p.estado=:estadoFiltro")
    public List<Pedido> searchPedidosByEstado(@Param("estadoFiltro") EstadoPedido estadoFiltro);
    @Query(value="SELECT * FROM pedido WHERE id_cliente=:idCliente",
            nativeQuery = true)
    public Page<Pedido> getAllByCliente(@Param("idCliente") Long idCliente, Pageable pageable);
    @Query(value="SELECT p FROM Pedido p WHERE YEAR(p.fechaPedido) = YEAR(:fechaActual) AND MONTH(p.fechaPedido) = MONTH(:fechaActual) AND DAY(p.fechaPedido) = DAY(:fechaActual)",
            countQuery = "SELECT count(p) FROM Pedido p WHERE YEAR(p.fechaPedido) = YEAR(:fechaActual) AND MONTH(p.fechaPedido) = MONTH(:fechaActual) AND DAY(p.fechaPedido) = DAY(:fechaActual)")
    public Page<Pedido> getPedidosActuales(@Param("fechaActual") Date fechaActual, Pageable pageable);
    @Query(value = "SELECT p FROM Pedido p WHERE p.estado = :estadoFiltro AND YEAR(p.fechaPedido) = YEAR(:fechaActual) AND MONTH(p.fechaPedido) = MONTH(:fechaActual) AND DAY(p.fechaPedido) = DAY(:fechaActual)",
            countQuery = "SELECT COUNT(p) FROM Pedido p WHERE p.estado = :estadoFiltro AND YEAR(p.fechaPedido) = YEAR(:fechaActual) AND MONTH(p.fechaPedido) = MONTH(:fechaActual) AND DAY(p.fechaPedido) = DAY(:fechaActual)")
    public Page<Pedido> getPedidosByEstado(@Param("estadoFiltro") EstadoPedido estadoFiltro, @Param("fechaActual") Date fechaActual, Pageable pageable);
    @Query(value="SELECT p FROM Pedido p WHERE p.nroPedido=:numeroPedido")
    public Pedido searchPedidosByNumero(@Param("numeroPedido") Long nroPedido);
}
