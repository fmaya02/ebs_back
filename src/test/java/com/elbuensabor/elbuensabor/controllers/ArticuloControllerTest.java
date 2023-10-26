package com.elbuensabor.elbuensabor.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.elbuensabor.elbuensabor.entities.Articulo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Date;

import com.elbuensabor.elbuensabor.services.ArticuloServiceImpl;
import com.elbuensabor.elbuensabor.dtos.DTOArticulosMasVendidos;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class ArticuloControllerTest {

    @MockBean
    private ArticuloServiceImpl articuloService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void findMostSoldTest() throws Exception {

        String fechaInicioString = "01/01/2003";
        String fechaFinString = "02/01/2003";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date fechaInicio = dateFormat.parse(fechaInicioString);
        Date fechaFin = dateFormat.parse(fechaFinString);

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

        Pageable pageable = PageRequest.of(0,2);
        Page<DTOArticulosMasVendidos> pageTest = new PageImpl<>(listaDto, pageable, 2);

        when(articuloService.findMostSold(fechaInicioString, fechaFinString, 0, 2)).thenReturn(listaDto);

        this.mockMvc.perform(get("/ebs/articulo/findMostSold")
                .param("date1", fechaInicioString)
                .param("date2", fechaFinString)
                .param("page", "0")
                .param("size", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].denominacionArticulo", is("Empanadas de carne")))
                .andExpect(jsonPath("$[0].cantidad", is(3)))
                .andExpect(jsonPath("$[1].denominacionArticulo", is("Empanadas de jamon y queso")))
                .andExpect(jsonPath("$[1].cantidad", is(8)));
    }

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

        when(articuloService.findByName(pageable, "Pizza")).thenReturn(pageTest);

        this.mockMvc.perform(get("/ebs/articulo/findByName")
                        .param("name", "Pizza")
                        .param("page", "0")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].denominacion", is("Pizza fugazzeta")));
    }

}
