package com.elbuensabor.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name="articulo_insumo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticuloInsumo extends BaseEntity{
    @NotNull
    @Column(name = "cantidad", precision = 10, scale = 2)
    private BigDecimal cantidad;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insumo_id")
    private Insumo insumo;

}
