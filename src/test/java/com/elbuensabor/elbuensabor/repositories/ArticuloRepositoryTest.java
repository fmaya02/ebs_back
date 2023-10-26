package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Articulo;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class ArticuloRepositoryTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ArticuloRepository articuloRepository;

    @Test
    @Rollback
    public void findByNameTest(){

        Articulo articulo = Articulo.builder()
                .denominacion("Pizza con piña, que asco")
                .descripcion("En serio, que asco")
                .precioVenta(new BigDecimal(4000))
                .fechaBaja(null)
                .urlImagen("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
                .build();

        List<Articulo> listaTest = new ArrayList<>();
        listaTest.add(articulo);

        Pageable pageable = PageRequest.of(0, 5);
        Page<Articulo> pageTest = new PageImpl<>(listaTest, pageable, 5);

        entityManager.persist(articulo);
        entityManager.flush();
        //tuve que comentar el main porque sino el repositorio me recuperaba las instancias del hardcode
        //comparar los page directamente genera error, hay que comparar unicamente su contenido
        assertEquals(pageTest.getContent(), articuloRepository.findByName("Pizza", pageable).getContent());
        assertEquals(pageTest.getContent(), articuloRepository.findByName("piña", pageable).getContent());
    }


}
