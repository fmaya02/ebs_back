package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Cliente;
import com.elbuensabor.elbuensabor.entities.Pedido;
import com.elbuensabor.elbuensabor.enums.EstadoPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {
    @Query(value="SELECT p FROM Pedido p WHERE p.estado=:filtro")
    public List<Pedido> searchPedidosByEstado(@Param("filtro") EstadoPedido estadoFiltro);
    @Query(value="SELECT * FROM pedido WHERE id_cliente=:filtro",
    nativeQuery = true)
    public Page<Pedido> getAllByCliente(@Param("filtro") Long idCliente, Pageable pageable);


}
