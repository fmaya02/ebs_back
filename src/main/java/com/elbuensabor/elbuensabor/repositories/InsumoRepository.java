package com.elbuensabor.elbuensabor.repositories;
import com.elbuensabor.elbuensabor.dtos.DTOArticulosBajoStock;
import com.elbuensabor.elbuensabor.entities.Insumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsumoRepository extends BaseRepository<Insumo, Long> {

    @Query(value = "SELECT i.denominacion AS denominacion, i.stockMinimo AS stockMinimo, i.stockActual AS stockActual, " +
                    "u.denominacion AS unidadMedida, (i.stockActual - i.stockMinimo) AS diferencia " +
                    "FROM Insumo i " +
                    "JOIN i.unidadMedida u " +
                    "WHERE stockActual <= (1.20 * stockMinimo)"
    )
    public List<DTOArticulosBajoStock> findLowStock(Pageable pageable);

}
