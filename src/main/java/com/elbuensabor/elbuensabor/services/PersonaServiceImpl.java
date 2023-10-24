package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Persona;
import com.elbuensabor.elbuensabor.entities.Usuario;
import com.elbuensabor.elbuensabor.enums.Rol;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.PersonaRepository;
import com.elbuensabor.elbuensabor.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
