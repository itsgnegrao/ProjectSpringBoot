package itsgnegrao.ProjectSpringBoot.service;

import itsgnegrao.ProjectSpringBoot.models.Cliente;
import itsgnegrao.ProjectSpringBoot.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> retornaAll() {
        return clienteRepository.findAll();
    }
}
