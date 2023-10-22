package com.elbuensabor.elbuensabor.dtos;

import jakarta.persistence.EntityResult;
import jakarta.persistence.FieldResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
@SqlResultSetMapping(
        name = "productosMasVendidosMapping",
        entities = {
                @EntityResult(
                        entityClass = DTOArticulosMasVendidos.class,
                        fields = {
                                @FieldResult(name = "cantidad", column = "cantidad"),
                                @FieldResult(name = "articuloId", column = "articuloId"),
                                @FieldResult(name = "denominacionArticulo", column = "denominacionArticulo"),
                                @FieldResult(name = "urlImagen", column = "urlImagen"),
                                @FieldResult(name = "denominacionRubro", column = "denominacionRubro")
                        }
                )
        }
)
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DTOArticulosMasVendidos {
    private int cantidad;
    private int articuloId;
    private String denominacionArticulo;
    private String urlImagen;
    private String denominacionRubro;
}























