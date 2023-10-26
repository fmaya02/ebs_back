package com.elbuensabor.elbuensabor.controllers;

import com.elbuensabor.elbuensabor.services.UsuarioServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.*;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class UsuarioControllerTest {

    @MockBean
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSignIn() throws Exception {
        String username = "usuario@gmail.com";
        String password = "Tiochaval#";
        String success = "El usuario usuario@gmail.com se ha logeado correctamente. ";

        try {
            when(usuarioService.signIn("usuario@gmail.com", "Tiochaval#")).thenReturn("El usuario usuario@gmail.com se ha logeado correctamente. ");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

            mockMvc.perform(post("/ebs/usuarios/signIn")
                    .param("username", "usuario@gmail.com")
                    .param("password", "Tiochaval#")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string("El usuario usuario@gmail.com se ha logeado correctamente. "));
    }
}
