package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dto.DTOCliente;
import com.elbuensabor.elbuensabor.mappers.PersonaMapper;
import com.elbuensabor.elbuensabor.entities.Persona;
import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.enums.Rol;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona,Long> implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    //todo poner el personamapper aca para poder hacer los updates de los campos necesarios
    @Autowired
    private UsuarioServiceImpl usuarioService;

    public PersonaServiceImpl(BaseRepository<Persona,Long> baseRepository, PersonaRepository personaRepository){
        super(baseRepository);
        this.personaRepository = personaRepository;
    }

    @Override
    public Page<Persona> searchPersonaApellido(String apellido, Pageable pageable) throws Exception {
        try {
            return personaRepository.searchPersonaApellido(apellido, pageable);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Persona> searchPersonaNombre(String nombre, Pageable pageable) throws Exception {
        try {
            return personaRepository.searchPersonaNombre(nombre, pageable);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Persona signUp(Persona persona, Rol rol, String pswd1, String pswd2) throws Exception {
        try {
            List<Persona> mailcheck = personaRepository.findPersonaByEmail(persona.getEmail());
            Usuario newUsuario = usuarioService.createUsuario(persona, rol, pswd1, pswd2, mailcheck);
            persona.setUsuario(newUsuario);
            persona.setFechaBaja(null);
            persona.setFechaModificacion(new Date());
            persona.setFechaAlta(new Date());
            return personaRepository.save(persona);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Persona> findPersonaByEmail(String email) throws Exception {
        try {
            return personaRepository.findPersonaByEmail(email);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String signIn(String email, String password) throws Exception {
        try {
            List<Persona> personas = this.findPersonaByEmail(email);
            if (personas.isEmpty()){
                throw new Exception("No existe usuario con este mail, por favor verifique los datos");
            }
            if (personas.size()>1){
                throw new Exception("Ha ocurrido un error, por favor intente más tarde");
            }
            if ( !(usuarioService.checkPassword(email, password) )) {
                throw new Exception("La contraseña ingresada es incorrecta, intente nuevamente");
            }
            if (personas.get(0).getFechaBaja()== null) {
                String result ="El usuario " + email + " se ha logeado correctamente. ";

                if ( usuarioService.firstTimeEmpleado(personas.get(0).getUsuario()) && !(personas.get(0).getUsuario().getRol().equals(Rol.CLIENTE)) ){
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
    public Persona updateCliente(DTOCliente dtoCliente) throws Exception {
        try {
            Persona persona = this.findById(dtoCliente.getId());
            PersonaMapper.updateClienteFromDto(dtoCliente, persona);
            return personaRepository.save(persona);


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Persona updateEmpleado(Persona persona, Long id, Rol rol) throws Exception {
        try {
            if (persona.getId() != id){
                throw new Exception("El id del body no es igual al seleccionado, por favor verificar los datos");
            }
            Persona personaOld = this.findById(id);
            String oldPassword = personaOld.getUsuario().getContraseña();
            persona.getUsuario().setContraseña(oldPassword);
            if (rol == null){
                persona.getUsuario().setRol(personaOld.getUsuario().getRol());
            }


            return this.update(id, persona);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
