package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dtos.DTOArticulosMasVendidos;
import com.elbuensabor.elbuensabor.entities.Articulo;
import com.elbuensabor.elbuensabor.repositories.ArticuloRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class ArticuloServiceTest {

    @MockBean
    private ArticuloRepository articuloRepository;

    @MockBean
    private DetallePedidoServiceImpl detallePedidoServiceImpl;

    @Autowired
    private ArticuloServiceImpl articuloService;

    @Test
    public void findByNameTest() throws Exception {
        Articulo articulo = Articulo.builder()
                .denominacion("Pizza fugazzeta")
                .descripcion("Que ricoo")
                .precioVenta(new BigDecimal(4000))
                .fechaBaja(null)
                .urlImagen("https://www.youtube.com/watch?v=KWZ-ytC9Uyk")
                .build();

        List<Articulo> listaTest = new ArrayList<>();
        listaTest.add(articulo);

        Pageable pageable = PageRequest.of(0, 5);
        Page<Articulo> pageTest = new PageImpl<>(listaTest, pageable, 5);

        when(articuloRepository.findByName("Pizza", pageable)).thenReturn(pageTest);

        assertEquals(pageTest.getContent(), this.articuloService.findByName(pageable, "Pizza").getContent());
    }

    @Test
    public void findMostSoldTest() throws Exception {
        DTOArticulosMasVendidos dto1 = DTOArticulosMasVendidos.builder()
                .denominacionArticulo("Empanadas de carne")
                .cantidad(Long.valueOf(3))
                .build();
        DTOArticulosMasVendidos dto2 = DTOArticulosMasVendidos.builder()
                .denominacionArticulo("Empanadas de jamon y queso")
                .cantidad(Long.valueOf(8))
                .build();

        List<DTOArticulosMasVendidos> listaDto = new ArrayList<>();
        listaDto.add(dto1);
        listaDto.add(dto2);

//        Pageable pageable = PageRequest.of(0,2);
//        Page<DTOArticulosMasVendidos> pageTest = new PageImpl<>(listaDto, pageable, 2);

        String fechaInicioString = "2003-01-01 15:30:00";
        String fechaFinString = "2030-01-20 15:30:30";
        String fechaPedidoString = "2023-01-01 00:00:00";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date fechaInicio = dateFormat.parse(fechaInicioString);
        Date fechaFin = dateFormat.parse(fechaFinString);
        Date fechaPedido = dateFormat.parse(fechaPedidoString);

        when(detallePedidoServiceImpl.findMostSold(fechaInicio, fechaFin)).thenReturn(listaDto);

        assertEquals(listaDto, articuloService.findMostSold(fechaInicioString, fechaFinString));


    }
}
