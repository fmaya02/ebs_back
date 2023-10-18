package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

    @Query(value = "SELECT u FROM Usuario u WHERE u.rol LIKE :rol")
    Page<Usuario> searchUsuarioRol(@Param("rol") String rol, Pageable pageable);

    @Query(value = "SELECT u FROM Usuario u WHERE u.username LIKE %:username%")
    Page<Usuario> searchUsuarioUsrnm(@Param("username") String username, Pageable pageable);
}
