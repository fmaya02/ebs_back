package com.elbuensabor.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name="detalle_comprobante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder=true)
public class DetalleComprobante extends BaseEntity{
    @NotNull
    private Integer cantidad;

    @NotNull
    @Column(name = "subtotal", precision = 10, scale = 2)
    private BigDecimal subtotal;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;
}
