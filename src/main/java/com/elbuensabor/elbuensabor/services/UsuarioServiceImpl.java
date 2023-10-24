package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Persona;
import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.enums.Rol;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.*;

import java.util.Date;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static String auth0counter = "1"; //esto simula la generacion de una id por auth0
    private static int auth0int =1;

    private static final String pswdRegex = "^(?=.*[a-z])(?=."
                                + "*[A-Z])"
                                + "(?=.*[-+_!@#$%^&*., ?]).+$";

    private static final String emailRegex = "^[a-zA-Z0-9_! #$%&'*+/=?`{|}~^. -]+@[a-zA-Z0-9. -]+$";

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository, UsuarioRepository usuarioRepository){
        super(baseRepository);
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Page<Usuario> searchUsuarioRol(String rol, Pageable pageable) throws Exception {
        try{
            return usuarioRepository.searchUsuarioRol(rol, pageable);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Usuario> searchUsuarioUsrnm(String username, Pageable pageable) throws Exception {
        try{
            return usuarioRepository.searchUsuarioUsrnm(username, pageable);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Usuario createUsuario(Persona persona, Rol rol, String pswd1, String pswd2, List<Persona> mailcheck) throws Exception {
        try {
            if (rol.equals(Rol.ADMINISTRADOR)) {
                throw new Exception("No puede crearse el usuario con el rol seleccionado");
            }

            if (!pswd1.equals(pswd2)){
                throw new Exception("La contraseña no coincide en ambos campos");
            }

            Pattern patternpswd = Pattern.compile(pswdRegex);
            Matcher matcherpswd = patternpswd.matcher(pswd1);
            if ( !(pswd1.length()>=8 && matcherpswd.matches() ) ){
                throw new Exception("La contraseña no cumple con las indicaciones, intente nuevamente");
            }

            String email = persona.getEmail();
            if ( !(mailcheck.isEmpty()) ){
                throw new Exception("Ya existe un usuario registrado con este email, intente nuevamente");
            }

            Pattern patternemail = Pattern.compile(emailRegex);
            Matcher matcheremail = patternemail.matcher(email);
            if (!matcheremail.matches()){
                throw new Exception("La direccion de correo electrónico no es válida, intente nuevamente");
            }

            Usuario newUsuario = Usuario.builder()
                    .fechaAlta(new Date())
                    .rol(rol)
                    .username("" + persona.getNombre().toLowerCase() + persona.getApellido().toLowerCase() + "")
                    .contraseña(pswd1)
                    .auth0Id(auth0counter)
                    .build();
            auth0int = auth0int + 1;
            auth0counter = "" + auth0int + "";
            usuarioRepository.save(newUsuario);
            return newUsuario;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
