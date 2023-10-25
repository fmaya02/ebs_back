package com.elbuensabor.elbuensabor.entities;

import com.elbuensabor.elbuensabor.enums.FormaPago;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="factura")
@Data
@SuperBuilder(toBuilder=true)
public class Factura extends Comprobante{

    @NotNull
    @Column(name = "fecha_facturacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFacturacion;

    @Column(name = "mp_payment_id")
    private Long mpPaymentId;

    @Column(name = "mp_merchant_order_id")
    private Long mpMerchantOrderId;

    @Column(name = "mp_preference_id")
    private String mpPreferenceId;

    @Column(name = "mp_payment_type")
    private String mpPaymentType;

    @NotNull
    private FormaPago formaPago;

    @Column(name = "fecha_anulacion")
    @Nullable
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnulacion;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    public void setFacturaDetalles(List<DetalleComprobante> detalles){
        this.facturaDetalles=detalles;
    }

}
