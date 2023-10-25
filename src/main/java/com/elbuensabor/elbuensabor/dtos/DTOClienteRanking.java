package com.elbuensabor.elbuensabor.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOClienteRanking {
    private String nombre;
    private String apellido;
    private int cantPedidos;
    private BigDecimal importeTotal;
}
