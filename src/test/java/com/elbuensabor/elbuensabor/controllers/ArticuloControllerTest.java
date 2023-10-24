package com.elbuensabor.elbuensabor.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Date;

import com.elbuensabor.elbuensabor.services.ArticuloServiceImpl;
import com.elbuensabor.elbuensabor.dtos.DTOArticulosMasVendidos;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArticuloControllerTest {

    @MockBean
    private ArticuloServiceImpl articuloService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void findMostSoldTest() throws Exception {
        DTOArticulosMasVendidos dto1 = DTOArticulosMasVendidos.builder()
                .denominacionArticulo("Empanadas de carne")
                .cantidad(3)
                .build();
        DTOArticulosMasVendidos dto2 = DTOArticulosMasVendidos.builder()
                .denominacionArticulo("Empanadas de jamon y queso")
                .cantidad(8)
                .build();

        List<DTOArticulosMasVendidos> listaDto = new ArrayList<>();
        listaDto.add(dto1);
        listaDto.add(dto2);

        Pageable pageable = PageRequest.of(0,2);
        Page<DTOArticulosMasVendidos> pageTest = new PageImpl<>(listaDto, pageable, 2);

        String fechaInicioString = "01/01/2003";
        String fechaFinString = "02/01/2003";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date fechaInicio = dateFormat.parse(fechaInicioString);
        Date fechaFin = dateFormat.parse(fechaFinString);

        System.out.println(fechaInicio + "----" + fechaFin);

        when(articuloService.findMostSold(fechaInicio, fechaFin, pageable)).thenReturn(pageTest);


        this.mockMvc.perform(get("/ebs/articulo/findMostSold")
                .param("date1", fechaInicioString)
                .param("date2", fechaFinString)
                .param("page", "0")
                .param("size", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].denominacionArticulo", is("Empanadas de carne")))
                .andExpect(jsonPath("$.content[0].cantidad", is(3)))
                .andExpect(jsonPath("$.content[1].denominacionArticulo", is("Empanadas de jamon y queso")))
                .andExpect(jsonPath("$.content[1].cantidad", is(8)));
    }

}
