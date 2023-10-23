package com.elbuensabor.elbuensabor.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class NotaCredito extends Comprobante{

    @OneToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;

}
