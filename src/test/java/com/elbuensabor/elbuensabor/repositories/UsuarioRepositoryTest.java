package com.elbuensabor.elbuensabor.repositories;

import com.elbuensabor.elbuensabor.entities.Usuario;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UsuarioRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    void testValidateUser(){
        Usuario usuario = new Usuario();
        usuario.setUsername("pedroramirez@hotmail.com");
        usuario.setContraseña("chinquErio#");
        usuario.setFechaAlta(new Date());
        usuario.setAuth0Id("1");

        List<Usuario> listaEnviada = new ArrayList<>();
        listaEnviada.add(usuario);

        entityManager.persist(usuario);
        entityManager.flush();

        assertEquals(listaEnviada, usuarioRepository.validateUser("pedroramirez@hotmail.com", "chinquErio#"));
    }

    @Test
    void testFindUsrByUsrname(){
        Usuario usuario = new Usuario();
        usuario.setUsername("lucaslucero@gmail.com");
        usuario.setContraseña("yerITla$");
        usuario.setFechaAlta(new Date());
        usuario.setAuth0Id("1");

        List<Usuario> listaEnviada = new ArrayList<>();
        listaEnviada.add(usuario);

        entityManager.persist(usuario);
        entityManager.flush();

        assertEquals(listaEnviada, usuarioRepository.findUsrByUsrname("lucaslucero@gmail.com"));
    }
}
