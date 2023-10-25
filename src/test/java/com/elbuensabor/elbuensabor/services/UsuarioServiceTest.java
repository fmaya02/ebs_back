package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.*;
@SpringBootTest
public class UsuarioServiceTest {

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Test
    void testCheckPassword(){
        Usuario usuario = new Usuario();
        usuario.setUsername("troyeronico@yahoo.com.ar");
        usuario.setContraseña("yaARgh&l");
        usuario.setFechaAlta(new Date());
        usuario.setAuth0Id("1");

        List<Usuario> listaEnviada = new ArrayList<>();
        listaEnviada.add(usuario);

        when(usuarioRepository.validateUser("troyeronico@yahoo.com.ar","yaARgh&l")).thenReturn(listaEnviada);

        try {
            assertTrue(usuarioService.checkPassword("troyeronico@yahoo.com.ar","yaARgh&l"));
            assertFalse(usuarioService.checkPassword("lucaslucer@gmail.com","añlskjT$#"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFirstTimeEmpleado(){
        Usuario usuario1 = new Usuario();
        usuario1.setUsername("userone@yahoo.com.ar");
        usuario1.setContraseña("tiochavAl#");
        usuario1.setFechaAlta(new Date());
        usuario1.setAuth0Id("1");
        usuario1.setHasLoggedIn(false);

        Usuario usuario2 = new Usuario();
        usuario2.setUsername("userone@yahoo.com.ar");
        usuario2.setContraseña("tiochavAl#");
        usuario2.setFechaAlta(new Date());
        usuario2.setAuth0Id("1");
        usuario2.setHasLoggedIn(true);

        when(usuarioRepository.save(usuario1)).thenReturn(usuario1);
        when(usuarioRepository.save(usuario2)).thenReturn(usuario2);

        try {
            assertTrue(usuarioService.firstTimeEmpleado(usuario1));
            assertFalse(usuarioService.firstTimeEmpleado(usuario2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
