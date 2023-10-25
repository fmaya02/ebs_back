package com.elbuensabor.elbuensabor.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MappedSuperclass
public abstract class Comprobante extends BaseEntity{

    @Column(name = "fecha_comprobante")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaComprobante;

    @Column(name = "nro_comprobante")
    private int nro;

    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name="detalle_comprobante")
    List<DetalleComprobante> facturaDetalles=new ArrayList<>();
}