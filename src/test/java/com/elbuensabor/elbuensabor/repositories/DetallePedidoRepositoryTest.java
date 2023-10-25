package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.dtos.DTOArticulosMasVendidos;
import com.elbuensabor.elbuensabor.entities.*;
import com.elbuensabor.elbuensabor.enums.EstadoPedido;
import com.elbuensabor.elbuensabor.enums.TipoEnvio;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class DetallePedidoRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Test
    @Rollback
    public void findMostSoldTest() throws ParseException {
        String fechaInicioString = "2003-01-01 15:30:00";
        String fechaFinString = "2030-01-20 15:30:30";
        String fechaPedidoString = "2023-01-01 00:00:00";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date fechaInicio = dateFormat.parse(fechaInicioString);
        Date fechaFin = dateFormat.parse(fechaFinString);
        Date fechaPedido = dateFormat.parse(fechaPedidoString);

        Localidad localidad1 = Localidad.builder()
                .denominacion("Kaer Morhen")
                .fechaAlta(fechaPedido)
                .build();
        Domicilio domicilio1 = Domicilio.builder()
                .calle("Novigrado")
                .codigoPostal(5500)
                .numero(123)
                .localidad(localidad1)
                .build();
        Persona cliente1 = Persona.builder()
                .nombre("Geralt")
                .apellido("de Rivia")
                .fechaAlta(fechaPedido)
                .email("geralt.derivia@kaermorhen.com")
                .domicilios(new ArrayList<Domicilio>())
                .build();
        cliente1.addDomicilio(domicilio1);
        RubroArticulo rubroArticulo1 = RubroArticulo.builder()
                .fechaAlta(fechaPedido)
                .denominacion("Pizza")
                .build();
        Articulo articulo1 = Articulo.builder()
                .costo(new BigDecimal(30000))
                .fechaAlta(fechaPedido)
                .denominacion("Pizza con piña")
                .descripcion("que asco")
                .articuloRubro(rubroArticulo1)
                .build();
        ArrayList<DetallePedido> arraydetalle= new ArrayList<DetallePedido>();
        DetallePedido detalle1 = DetallePedido.builder()
                .articulo(articulo1)
                .subtotalCosto(articulo1.getCosto())
                .subtotal(articulo1.getCosto())
                .cantidad(1)
                .build();
        arraydetalle.add(detalle1);

        Pedido pedido1 = Pedido.builder()
                .fechaPedido(fechaPedido)
                .persona(cliente1)
                .domicilioEntrega(domicilio1)
                .tipoEnvio(TipoEnvio.DELIVERY)
                .estado(EstadoPedido.ENTREGADO)
                .pedidoDetalles(arraydetalle)
                .build();

        entityManager.persist(rubroArticulo1);
        entityManager.persist(articulo1);
        entityManager.persist(localidad1);
        entityManager.persist(domicilio1);
        entityManager.persist(cliente1);
        entityManager.persist(pedido1);
        entityManager.flush();

        DTOArticulosMasVendidos dto1 = DTOArticulosMasVendidos.builder()
                        .articuloId(1)
                        .denominacionArticulo("Pizza con piña")
                        .cantidad(Long.valueOf(1))
                        .urlImagen(null)
                        .denominacionRubro("Pizza")
                        .build();
        List<DTOArticulosMasVendidos> listaDto = new ArrayList<>();
        listaDto.add(dto1);


        assertEquals(listaDto, detallePedidoRepository.findMostSold(fechaInicio, fechaFin));

    }
}
