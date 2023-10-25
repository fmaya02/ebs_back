package com.elbuensabor.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder(toBuilder=true)

public class NotaCredito extends Comprobante{

    @OneToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;

    public NotaCredito() {
        super();
    }


    public void setFactura(Factura factura){this.factura = factura;}
}
