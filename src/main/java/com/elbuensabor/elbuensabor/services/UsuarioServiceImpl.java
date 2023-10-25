package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Persona;
import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.enums.Rol;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Usuario createUsuario(Persona persona, Rol rol, String pswd1, String pswd2, List<Persona> mailcheck) throws Exception {
        try {
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
                    .fechaModificacion(null)
                    .fechaBaja(null)
                    .rol(rol)
                    .username(email)
                    .contraseña(pswd1)
                    .auth0Id(auth0counter)
                    .hasLoggedIn(false)
                    .build();
            auth0int = auth0int + 1;
            auth0counter = "" + auth0int + "";
            return usuarioRepository.save(newUsuario);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String signIn(String username, String password) throws Exception {
        try {
            List<Usuario> usuarios = usuarioRepository.findUsrByUsrname(username);
            if (usuarios.isEmpty()){
                throw new Exception("No existe usuario con este mail, por favor verifique los datos");
            }
            if (usuarios.size()>1){
                throw new Exception("Ha ocurrido un error, por favor intente más tarde");
            }
            if ( !(this.checkPassword(username, password) )) {
                throw new Exception("La contraseña ingresada es incorrecta, intente nuevamente");
            }
            if (usuarios.get(0).getFechaBaja()== null) {
                String result ="El usuario " + username + " se ha logeado correctamente. ";

                if ( this.firstTimeEmpleado(usuarios.get(0)) && !(usuarios.get(0).getRol().equals(Rol.CLIENTE)) ){
                    result = result + "EL EMPLEADO DEBE CAMBIAR LA CONTRASEÑA";
                }

                return result;
            }else {
                return "Su usuario está dado de baja. Por favor consulte con el administrador";
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean checkPassword(String username, String password) throws Exception {
        try{
            List<Usuario> usercheck = usuarioRepository.validateUser(username, password);
            if (usercheck.size() != 1)
                return false;
            else
                return true;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean firstTimeEmpleado(Usuario usr) throws Exception {
        try{
            if (usr.isHasLoggedIn())
                return false;
            else {
                usr.setHasLoggedIn(true);
                usuarioRepository.save(usr);
                return true;
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
