package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.Jwt.JwtService;
import com.elbuensabor.elbuensabor.dtos.DTOLogin;
import com.elbuensabor.elbuensabor.dtos.DTORegistro;
import com.elbuensabor.elbuensabor.dtos.DTOToken;
import com.elbuensabor.elbuensabor.entities.Persona;
import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.enums.Rol;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.*;

import java.util.Date;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;

    private static String auth0counter = "1"; //esto simula la generacion de una id por auth0
    private static int auth0int =2;

    private static final String pswdRegex = "^(?=.*[a-z])(?=."
                                + "*[A-Z])"
                                + "(?=.*[-+_!@#$%^&*., ?]).+$";

    private static final String emailRegex = "^[a-zA-Z0-9_! #$%&'*+/=?`{|}~^. -]+@[a-zA-Z0-9. -]+$";

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository, UsuarioRepository usuarioRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager){
        super(baseRepository);
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Usuario createUsuario(DTORegistro dtoRegistro, List<Persona> mailcheck) throws Exception {
        try {

//            Pattern patternpswd = Pattern.compile(pswdRegex);
//            Matcher matcherpswd = patternpswd.matcher(dtoRegistro.getPassword());
//            if ( !(dtoRegistro.getPassword().length()>=8 && matcherpswd.matches() ) ){
//                throw new Exception("La contraseña no cumple con las indicaciones, intente nuevamente");
//            }
//
            String email = dtoRegistro.getEmail();
            if ( !(mailcheck.isEmpty()) ){
                throw new Exception("Ya existe un usuario registrado con este email, intente nuevamente");
            }
//
//            Pattern patternemail = Pattern.compile(emailRegex);
//            Matcher matcheremail = patternemail.matcher(email);
//            if (!matcheremail.matches()){
//                throw new Exception("La direccion de correo electrónico no es válida, intente nuevamente");
//            }
            auth0int = auth0int + 1;
            auth0counter = "" + auth0int + "";
            Usuario newUsuario = Usuario.builder()
                    .fechaAlta(new Date())
                    .fechaModificacion(null)
                    .fechaBaja(null)
                    .rol(Rol.valueOf(dtoRegistro.getRol()))
                    .username(email)
                    .password(passwordEncoder.encode(dtoRegistro.getPassword()))
                    .auth0Id(auth0counter)
                    .hasLoggedIn(false)
                    .build();

            return usuarioRepository.save(newUsuario);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public DTOToken signIn(DTOLogin dtoLogin) throws Exception {
        try {
            Optional<Usuario> usuario = usuarioRepository.findUsrByUsrname(dtoLogin.getUsername());
//            if (usuarios.isEmpty()){
//                throw new Exception("No existe usuario con este mail, por favor verifique los datos");
//            }
//            if (usuarios.size()>1){
//                throw new Exception("Ha ocurrido un error, por favor intente más tarde");
//            }
//            if ( !(this.checkPassword(username, password) )) {
//                throw new Exception("La contraseña ingresada es incorrecta, intente nuevamente");
//            }
//            if (usuario.get().getFechaBaja()== null) {
//                String result ="El usuario " + dtoLogin.getUsername() + " se ha logeado correctamente. ";
//
//                if ( this.firstTimeEmpleado(usuario.get()) && !(usuario.get().getRol().equals(Rol.CLIENTE)) ){
//                    result = result + "EL EMPLEADO DEBE CAMBIAR LA CONTRASEÑA";
//                }
//                throw new Exception(result);
////                return result;
//            }else {
//                throw new Exception("Su usuario está dado de baja. Por favor consulte con el administrador");
////                return "Su usuario está dado de baja. Por favor consulte con el administrador";
//            }
            if(usuario.get().getFechaBaja()!=null) throw new Exception("Su usuario está dado de baja. Por favor consulte con el administrador");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoLogin.getUsername(), dtoLogin.getPassword()));
            UserDetails user= (UserDetails) usuarioRepository.findUsrByUsrname(dtoLogin.getUsername()).orElseThrow();
            String token=jwtService.getToken(user);
            return DTOToken.builder()
                    .token(token)
                    .build();
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
