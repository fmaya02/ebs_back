package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
}
