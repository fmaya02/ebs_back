package com.elbuensabor.elbuensabor.dtos;


import jakarta.persistence.EntityResult;
import jakarta.persistence.FieldResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@SqlResultSetMapping(
        name = "findLowStock",
        entities = {
                @EntityResult(
                        entityClass = DTOArticulosBajoStock.class,
                        fields = {
                                @FieldResult(name = "denominacion", column = "denominacion"),
                                @FieldResult(name = "stockMinimo", column = "stockMinimo"),
                                @FieldResult(name = "stockActual", column = "stockActual"),
                                @FieldResult(name = "unidadMedida", column = "unidadMedida"),
                                @FieldResult(name = "diferencia", column = "diferencia")
                        }
                )
        }
)
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DTOArticulosBajoStock {
    private String denominacion;
    private int stockMinimo;
    private int stockActual;
    private String unidadMedida;
    private int diferencia;
}
