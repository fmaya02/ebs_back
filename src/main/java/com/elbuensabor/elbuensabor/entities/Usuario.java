package com.elbuensabor.elbuensabor.entities;

import com.elbuensabor.elbuensabor.enums.Rol;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder=true)
public class Usuario extends BaseEntity implements UserDetails{
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
    private String password;

    @Column(name = "hasloggedin")
    private boolean hasLoggedIn;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //que rol tiene mi usuario
        return List.of(new SimpleGrantedAuthority((rol.name())));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
