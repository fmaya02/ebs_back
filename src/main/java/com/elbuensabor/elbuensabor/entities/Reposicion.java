package com.elbuensabor.elbuensabor.entities;

import com.elbuensabor.elbuensabor.enums.EstadoPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "reposicion")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder=true)
public class Reposicion extends BaseEntity{

    @NotNull
    @Column(name = "fecha_reposicion")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fechaReposicion;

    @NotNull
    @Column(name = "estado_reposicion")
    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "reposicion_id")
    private List<ReposicionInsumo> listaInsumos = new ArrayList<ReposicionInsumo>();


}
