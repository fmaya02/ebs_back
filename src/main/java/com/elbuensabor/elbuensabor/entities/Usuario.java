package com.elbuensabor.elbuensabor.entities;

import com.elbuensabor.elbuensabor.enums.Rol;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Table(name="usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario extends BaseEntity{
    @NotNull
    @Column(name = "auth0_id", nullable = false, unique = true)
    private String auth0Id;

    @NotNull
    @Column(name = "username", nullable = false)
    private String username;

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

    @Column(name = "rol")
    @Enumerated(value = EnumType.STRING)
    private Rol rol;

    @NotNull
    @Column(name = "contraseña")
    private String contraseña;

    @Column(name = "hasloggedin")
    private boolean hasLoggedIn;
}
