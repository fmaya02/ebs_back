package com.elbuensabor.elbuensabor.services;

import com.elbuensabor.elbuensabor.entities.Cliente;
import com.elbuensabor.elbuensabor.repositories.BaseRepository;
import com.elbuensabor.elbuensabor.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente,Long> implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteServiceImpl(BaseRepository<Cliente,Long> baseRepository, ClienteRepository clienteRepository){
        super(baseRepository);
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Page<Cliente> searchClienteApellido(String apellido, Pageable pageable) throws Exception {
        try {
            Page<Cliente> clientes = clienteRepository.searchClienteApellido(apellido, pageable);
            return clientes;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Cliente> searchClienteNombre(String nombre, Pageable pageable) throws Exception {
        try {
            Page<Cliente> clientes = clienteRepository.searchClienteNombre(nombre, pageable);
            return clientes;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
