package com.elbuensabor.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Entity
@Table(name = "reposicion_insumo")
@AllArgsConstructor
@NoArgsConstructor
public class ReposicionInsumo extends BaseEntity{

    @NotNull
    @Column(name = "cantidad")
    private int cantidad;

    @NotNull
    @Column(name = "costo")
    private float costo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insumo_id")
    private Insumo insumo;
}
