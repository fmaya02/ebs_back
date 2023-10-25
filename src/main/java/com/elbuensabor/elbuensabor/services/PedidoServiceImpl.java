package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.*;
import com.elbuensabor.elbuensabor.enums.EstadoPedido;
import com.elbuensabor.elbuensabor.enums.TipoEnvio;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.InsumoRepository;
import com.elbuensabor.elbuensabor.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido, Long> implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private InsumoRepository insumoRepository;
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

            //descontar stock y setear totalCosto
            pedido.setTotalCosto(descontarStock(pedido.getPedidoDetalles()));
            pedido=save(pedido);
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

    @Override
    public Page<Pedido> getPedidosByEstadoAndFecha(EstadoPedido estadoPedido, Pageable pageable) throws Exception {
        try{
            return pedidoRepository.getPedidosByEstado(estadoPedido, new Date(), pageable);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Pedido> getPedidosActuales(Pageable pageable) throws Exception {
        try{
            return pedidoRepository.getPedidosActuales(new Date(), pageable);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Pedido updateEstado(EstadoPedido estadoPedido, Long idPedido) throws Exception {
        try{
            Pedido pedidoActual=this.findById(idPedido);
            pedidoActual.setEstado(estadoPedido);
            return this.save(pedidoActual);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Pedido searchPedidoByNumero(Long nroPedido) throws Exception {
        try{
            return pedidoRepository.searchPedidosByNumero(nroPedido);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private BigDecimal descontarStock(List<DetallePedido> detallesPedido){
        BigDecimal totalCosto=new BigDecimal(0);
        for(DetallePedido detallePedido:detallesPedido){
            Articulo articulo=detallePedido.getArticulo();
            BigDecimal costo=articulo.getCosto().multiply(new BigDecimal(detallePedido.getCantidad()));
            totalCosto=totalCosto.add(costo);
            List<ArticuloInsumo> articuloInsumos=articulo.getArticuloInsumos();
            for(ArticuloInsumo articuloInsumo:articuloInsumos){
                Insumo insumo=articuloInsumo.getInsumo();
                BigDecimal stockActual=insumo.getStockActual();
                BigDecimal cantidadPedido=articuloInsumo.getCantidad().multiply(new BigDecimal(-1));
                insumo.setStockActual(stockActual.add(cantidadPedido));
                insumoRepository.save(insumo);
            }
        }
        return totalCosto;
    }

}
