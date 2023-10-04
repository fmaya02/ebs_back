package com.elbuensabor.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Table(name="unidad_medida")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadMedida extends BaseEntity{
    @NotNull
    @Column(length = 255)
    private String denominacion;

    @NotNull
    @Column(length = 25)
    private String abreviatura;

    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
}
