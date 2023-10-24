package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Articulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends BaseRepository<Articulo, Long>{


    @Query(value = "SELECT * " +
            "FROM ARTICULO a " +
            "WHERE DENOMINACION LIKE %:filtro% AND FECHA_BAJA IS NULL",
            nativeQuery = true
    )
    Page<Articulo> findByName(@Param("filtro") String filtro, Pageable pageable);


}
