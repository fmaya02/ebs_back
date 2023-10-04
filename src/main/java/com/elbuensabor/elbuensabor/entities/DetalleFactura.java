package com.elbuensabor.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.util.Date;
@Entity
@Table(name="detalle_factura")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleFactura extends BaseEntity{
    @NotNull
    private Integer cantidad;

    @NotNull
    @Column(name = "subtotal", precision = 10, scale = 2)
    private BigDecimal subtotal;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;
}
