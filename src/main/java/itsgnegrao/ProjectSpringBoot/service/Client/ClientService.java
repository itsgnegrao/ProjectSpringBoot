package itsgnegrao.ProjectSpringBoot.service.Client;

import itsgnegrao.ProjectSpringBoot.models.Cliente;
import itsgnegrao.ProjectSpringBoot.repository.Client.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> retornaAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public ResponseEntity<Object> deleteById(Long id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }


}
