package com.elbuensabor.elbuensabor.repositories;
import com.elbuensabor.elbuensabor.entities.Insumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumoRepository extends BaseRepository<Insumo, Long> {

    @Query(value = "SELECT i.DENOMINACION AS denominacion, i.STOCK_MINIMO AS stockMinimo, i.STOCK_ACTUAL AS stockActual, " +
                    "u.DENOMINACION AS unidadMedida, (i.STOCK_ACTUAL - i.STOCK_MINIMO) AS diferencia " +
                    "FROM INSUMO i " +
                    "INNER JOIN UNIDAD_MEDIDA u ON u.ID = i.UNIDAD_MEDIDA_ID " +
                    "WHERE STOCK_ACTUAL <= (1.20 * STOCK_MINIMO)",
            countQuery = "SELECT COUNT(*) FROM DETALLE_PEDIDO",
            nativeQuery = true
    )
    public Page<Insumo> findLowStock(Pageable pageable);

}
