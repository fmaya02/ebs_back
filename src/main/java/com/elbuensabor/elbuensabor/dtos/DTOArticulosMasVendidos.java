package com.elbuensabor.elbuensabor.dtos;

import jakarta.persistence.EntityResult;
import jakarta.persistence.FieldResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*@SqlResultSetMapping(
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
)*/
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class DTOArticulosMasVendidos {
    private Long cantidad;
    private long articuloId;
    private String denominacionArticulo;
    private String urlImagen;
    private String denominacionRubro;

}























