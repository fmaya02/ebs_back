package com.elbuensabor.elbuensabor.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona extends BaseEntity{
    private String nombre;

    private String apellido;

    private String telefono;

    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id")
    private List<Domicilio> domicilios=new ArrayList<>();

    public void setDomicilios(List<Domicilio> domicilios){
        this.domicilios=domicilios;
    }

    public void addDomicilio(Domicilio domicilio) {
        this.domicilios.add(domicilio);
    }

    public void setUsuario(Usuario usuario){ this.usuario = usuario;}

}
