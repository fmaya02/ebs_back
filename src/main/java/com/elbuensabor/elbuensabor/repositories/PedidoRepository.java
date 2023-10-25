package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {

    @Query(value="SELECT * FROM pedido WHERE id_cliente=:idCliente",
            nativeQuery = true)
    public List<Pedido> getAllByCliente(@Param("idCliente") Long idCliente);
}
