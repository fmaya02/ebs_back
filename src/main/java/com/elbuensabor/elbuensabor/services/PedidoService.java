package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Pedido;
import com.elbuensabor.elbuensabor.enums.EstadoPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PedidoService extends BaseService<Pedido, Long> {
    public List<Pedido> searchPedidosByEstado(EstadoPedido estadoPedido) throws Exception;

    public Pedido confirmPedido(Pedido pedido)throws Exception;

    public Page<Pedido> getAllByCliente(Long idCliente, Pageable pageable)throws Exception;

    public Page<Pedido> getPedidosByEstadoAndFecha(EstadoPedido estadoPedido, Pageable pageable) throws Exception;

    public Page<Pedido> getPedidosActuales(Pageable pageable) throws Exception;

    public Pedido updateEstado(EstadoPedido estadoPedido, Long idPedido) throws Exception;

    public Pedido searchPedidoByNumero(Long nroPedido) throws Exception;
}
