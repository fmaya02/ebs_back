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
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
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

        Localidad localidadTest = Localidad.builder()
                .denominacion("Kaer Morhen")
                .fechaAlta(fechaPedido)
                .build();
        Domicilio domicilioTest = Domicilio.builder()
                .calle("Novigrado")
                .codigoPostal(5500)
                .numero(123)
                .localidad(localidadTest)
                .build();
        Persona clienteTest = Persona.builder()
                .nombre("Geralt")
                .apellido("de Rivia")
                .fechaAlta(fechaPedido)
                .email("geralt.derivia@kaermorhen.com")
                .domicilios(new ArrayList<Domicilio>())
                .build();
        clienteTest.addDomicilio(domicilioTest);
        RubroArticulo rubroArticuloTest = RubroArticulo.builder()
                .fechaAlta(fechaPedido)
                .denominacion("Pizza")
                .build();
        Articulo articuloTest = Articulo.builder()
                .costo(new BigDecimal(30000))
                .fechaAlta(fechaPedido)
                .denominacion("Pizza con piña")
                .descripcion("que asco")
                .articuloRubro(rubroArticuloTest)
                .build();
        ArrayList<DetallePedido> arraydetalle= new ArrayList<DetallePedido>();
        DetallePedido detalleTest = DetallePedido.builder()
                .articulo(articuloTest)
                .subtotalCosto(articuloTest.getCosto())
                .subtotal(articuloTest.getCosto())
                .cantidad(1)
                .build();
        arraydetalle.add(detalleTest);

        Pedido pedidoTest = Pedido.builder()
                .fechaPedido(fechaPedido)
                .persona(clienteTest)
                .domicilioEntrega(domicilioTest)
                .tipoEnvio(TipoEnvio.DELIVERY)
                .estado(EstadoPedido.ENTREGADO)
                .pedidoDetalles(arraydetalle)
                .build();

        entityManager.persist(rubroArticuloTest);
        entityManager.persist(articuloTest);
        entityManager.persist(localidadTest);
        entityManager.persist(domicilioTest);
        entityManager.persist(clienteTest);
        entityManager.persist(pedidoTest);
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
