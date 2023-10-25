package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.dto.DTOCliente;
import com.elbuensabor.elbuensabor.dto.DTOEmpleado;
import com.elbuensabor.elbuensabor.entities.Persona;
import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.enums.EstadoPersona;
import com.elbuensabor.elbuensabor.enums.Rol;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona,Long> implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private UsuarioServiceImpl usuarioService;

    public PersonaServiceImpl(BaseRepository<Persona,Long> baseRepository, PersonaRepository personaRepository){
        super(baseRepository);
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona signUp(Persona persona, Rol rol, String pswd1, String pswd2) throws Exception {
        try {
            List<Persona> mailcheck = personaRepository.findPersonaByEmail(persona.getEmail());
            Usuario newUsuario = usuarioService.createUsuario(persona, rol, pswd1, pswd2, mailcheck);
            //Domicilio dom = domicilioService.save(persona.getDomiclios.get(0));
            persona.setUsuario(newUsuario);
            persona.setFechaBaja(null);
            persona.setFechaModificacion(new Date());
            persona.setFechaAlta(new Date());
            persona.setDomicilios(new ArrayList<>());
            //persona.addDomicilio(dom);
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
    public List<DTOCliente> getAllClientes() throws Exception {
        try {
            List<Persona> personas = personaRepository.getAllClientes();
            List<DTOCliente> dtos = new ArrayList<>();
            ModelMapper modelMapper = new ModelMapper();
            for (Persona persona : personas){
                DTOCliente dtoCliente = modelMapper.map(persona, DTOCliente.class);
                if (persona.getFechaBaja()==null){
                    dtoCliente.setEstadoPersona(EstadoPersona.ALTA);
                } else {
                    dtoCliente.setEstadoPersona(EstadoPersona.BAJA);
                }
                dtos.add(dtoCliente);
            }
            return dtos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<DTOEmpleado> getAllEmpleados() throws Exception {
        try {
            List<Persona> personas = personaRepository.getAllEmpleados();
            List<DTOEmpleado> dtos = new ArrayList<>();
            ModelMapper modelMapper = new ModelMapper();
            for (Persona persona : personas){
                DTOEmpleado dtoEmpleado = modelMapper.map(persona, DTOEmpleado.class);
                if (persona.getFechaBaja()==null){
                    dtoEmpleado.setEstadoPersona(EstadoPersona.ALTA);
                } else {
                    dtoEmpleado.setEstadoPersona(EstadoPersona.BAJA);
                }
                dtoEmpleado.setRol(persona.getUsuario().getRol());
                dtos.add(dtoEmpleado);
            }
            return dtos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public DTOCliente getCliente(Long id) throws Exception {
        try {
            Persona persona = this.findById(id);
            ModelMapper modelMapper = new ModelMapper();
            DTOCliente dtoCliente = modelMapper.map(persona, DTOCliente.class);
            if (persona.getFechaBaja()==null){
                dtoCliente.setEstadoPersona(EstadoPersona.ALTA);
            } else {
                dtoCliente.setEstadoPersona(EstadoPersona.BAJA);
            }
            if (persona.getUsuario().getRol()!=Rol.CLIENTE){
                throw new Exception("El id ingresado no corresponde con un cliente, verifique los datos");
            }
            return dtoCliente;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public DTOEmpleado getEmpleado(Long id) throws Exception {
        try {
            Persona persona = this.findById(id);
            ModelMapper modelMapper = new ModelMapper();
            DTOEmpleado dtoEmpleado = modelMapper.map(persona, DTOEmpleado.class);
            if (persona.getFechaBaja()==null){
                dtoEmpleado.setEstadoPersona(EstadoPersona.ALTA);
            } else {
                dtoEmpleado.setEstadoPersona(EstadoPersona.BAJA);
            }
            if (persona.getUsuario().getRol()==Rol.CLIENTE){
                throw new Exception("El id ingresado no corresponde con un Empleado, verifique los datos");
            }else {
                dtoEmpleado.setRol(persona.getUsuario().getRol());
            }
            return dtoEmpleado;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public DTOCliente updateCliente(DTOCliente dtoCliente, Long id) throws Exception {
        try {
            //para que esto funcione bien, el dto debe tener valores null en lo que no se quiera actualizar de la entidad
            //si esto no es así, se pisará lo que sea que valgan esas propiedades en el json en la entidad a actualizar
            ModelMapper modelMapper = new ModelMapper();
            Persona persona = findById(id);
            Persona personaIntermedia = modelMapper.map(dtoCliente, Persona.class);
            if (personaIntermedia.getNombre()!=null){
                persona.setNombre(personaIntermedia.getNombre());
            }
            if (personaIntermedia.getApellido()!=null){
                persona.setApellido(personaIntermedia.getApellido());
            }
            if (personaIntermedia.getTelefono()!=null){
                persona.setTelefono(personaIntermedia.getTelefono());
            }
            if (personaIntermedia.getEmail()!=null){
                persona.setEmail(personaIntermedia.getEmail());
            }
            if (dtoCliente.getEstadoPersona()==EstadoPersona.ALTA){
                persona.setFechaBaja(null);
                persona.getUsuario().setFechaBaja(null);
            }
            if (dtoCliente.getEstadoPersona()==EstadoPersona.BAJA){
                persona.setFechaBaja(new Date());
                persona.getUsuario().setFechaBaja(new Date());
            }
            usuarioService.save(persona.getUsuario());
            Persona personaPersistida = personaRepository.save(persona);
            DTOCliente dtoRetorno = modelMapper.map(personaPersistida, DTOCliente.class);
            if (personaPersistida.getFechaBaja()==null){
                dtoRetorno.setEstadoPersona(EstadoPersona.ALTA);
            } else {
                dtoRetorno.setEstadoPersona(EstadoPersona.BAJA);
            }
            return dtoRetorno;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public DTOEmpleado updateEmpleado(DTOEmpleado dtoEmpleado, Long id) throws Exception {
        try {
            //para que esto funcione bien, el dto debe tener valores null en lo que no se quiera actualizar de la entidad
            //si esto no es así, se pisará lo que sea que valgan esas propiedades en el json en la entidad a actualizar
            ModelMapper modelMapper = new ModelMapper();
            Persona persona = findById(id);
            Persona personaIntermedia = modelMapper.map(dtoEmpleado, Persona.class);
            if (personaIntermedia.getNombre()!=null){
                persona.setNombre(personaIntermedia.getNombre());
            }
            if (personaIntermedia.getApellido()!=null){
                persona.setApellido(personaIntermedia.getApellido());
            }
            if (personaIntermedia.getTelefono()!=null){
                persona.setTelefono(personaIntermedia.getTelefono());
            }
            if (personaIntermedia.getEmail()!=null){
                persona.setEmail(personaIntermedia.getEmail());
            }
            if (dtoEmpleado.getEstadoPersona()==EstadoPersona.ALTA){
                persona.setFechaBaja(null);
                persona.getUsuario().setFechaBaja(null);
            }
            if (dtoEmpleado.getEstadoPersona()==EstadoPersona.BAJA){
                persona.setFechaBaja(new Date());
                persona.getUsuario().setFechaBaja(new Date());
            }
            if (dtoEmpleado.getRol()!=null){
                persona.getUsuario().setRol(dtoEmpleado.getRol());
            }
            usuarioService.save(persona.getUsuario());
            Persona personaPersistida = personaRepository.save(persona);
            DTOEmpleado dtoRetorno = modelMapper.map(personaPersistida, DTOEmpleado.class);
            if (personaPersistida.getFechaBaja()==null){
                dtoRetorno.setEstadoPersona(EstadoPersona.ALTA);
            } else {
                dtoRetorno.setEstadoPersona(EstadoPersona.BAJA);
            }
            dtoRetorno.setRol(personaPersistida.getUsuario().getRol());
            return dtoRetorno;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
