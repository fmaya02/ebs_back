package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM usuario u WHERE u.username = :username AND u.contraseña = :password",
            nativeQuery = true)
    List<Usuario> validateUser(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT * FROM usuario u WHERE u.username = :username",
            nativeQuery = true)
    List<Usuario> findUsrByUsrname(@Param("username") String username);
}
