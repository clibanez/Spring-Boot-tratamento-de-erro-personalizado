package com.hortifruti.exception.services;

import com.hortifruti.exception.dto.ClienteDTO;
import com.hortifruti.exception.model.Cliente;
import com.hortifruti.exception.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Long id){

        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow();
    }

    public Cliente create(ClienteDTO clienteDTO){

        Cliente cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }
}
