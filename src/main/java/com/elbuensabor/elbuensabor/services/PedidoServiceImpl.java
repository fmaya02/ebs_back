package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.*;
import com.elbuensabor.elbuensabor.enums.EstadoPedido;
import com.elbuensabor.elbuensabor.enums.TipoEnvio;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido, Long> implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository) {
        super(baseRepository);
        this.baseRepository=baseRepository;
    }

    @Override
    public List<Pedido> searchPedidosByEstado(EstadoPedido estadoPedido) throws Exception{
        try{
            return pedidoRepository.searchPedidosByEstado(estadoPedido);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public Pedido confirmPedido(Pedido pedido)throws Exception{
        try{
            //calcular tiempo estimado
            int tiempoEstimadoArticuloPedido=0;
            for (DetallePedido detallePedido: pedido.getPedidoDetalles()) {
                if(detallePedido.getArticulo().getTiempoEstimadoCocina()>tiempoEstimadoArticuloPedido)
                    tiempoEstimadoArticuloPedido=detallePedido.getArticulo().getTiempoEstimadoCocina();
            }
            //pedido.setEstado(EstadoPedido.A_CONFIRMAR);   se setea desde el front
            int tiempoEstimadoPedidosEnCocina=0;
            int tiempoEstimadoArticuloEnCocina=0;
            List<Pedido> pedidosEnCocina=searchPedidosByEstado(EstadoPedido.EN_COCINA);
            for (Pedido pedidoCocina:pedidosEnCocina ) {
                for(DetallePedido detallePedido:pedidoCocina.getPedidoDetalles()){
                    if(detallePedido.getArticulo().getTiempoEstimadoCocina()>tiempoEstimadoArticuloEnCocina)
                        tiempoEstimadoArticuloEnCocina=detallePedido.getArticulo().getTiempoEstimadoCocina();
                }
                tiempoEstimadoPedidosEnCocina+=tiempoEstimadoArticuloEnCocina;
            }
            int tiempoTotalEsperado=pedido.getTipoEnvio()== TipoEnvio.DELIVERY?tiempoEstimadoPedidosEnCocina+tiempoEstimadoArticuloPedido+10:tiempoEstimadoPedidosEnCocina+tiempoEstimadoArticuloPedido;
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MINUTE, tiempoTotalEsperado);
            //setear hora estimada finalizacion
            pedido.setHoraEstimadaFinalizacion(calendar.getTime());
            pedido=save(pedido);
            //descontar stock
            descontarStock(pedido.getPedidoDetalles());
            return pedido;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Pedido> getAllByCliente(Long idCliente, Pageable pageable) throws Exception {
        try{
            return pedidoRepository.getAllByCliente(idCliente, pageable);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private void descontarStock(List<DetallePedido> detallesPedido){
        for(DetallePedido detallePedido:detallesPedido){
            Articulo articulo=detallePedido.getArticulo();
            List<ArticuloInsumo> articuloInsumos=articulo.getArticuloInsumos();
            for(ArticuloInsumo articuloInsumo:articuloInsumos){
                Insumo insumo=articuloInsumo.getInsumo();
                BigDecimal stockActual=insumo.getStockActual();
                insumo.setStockActual(stockActual.add(articuloInsumo.getCantidad().multiply(new BigDecimal(-1))));
            }
        }
    }

}
