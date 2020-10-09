package itsgnegrao.ProjectSpringBoot.resources.api;

import itsgnegrao.ProjectSpringBoot.models.Client;
import itsgnegrao.ProjectSpringBoot.models.ConsultaBody;
import itsgnegrao.ProjectSpringBoot.service.Client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static itsgnegrao.ProjectSpringBoot.configs.ConfigsFrontEnd.API_URL;
import static itsgnegrao.ProjectSpringBoot.configs.ConfigsFrontEnd.API_URL_LOCAL;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/client")
public class ClientResource {

    @Autowired
    ClientService clienteService;

    @CrossOrigin(origins = {API_URL, API_URL_LOCAL})
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = {"/get"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity buscar(@RequestBody ConsultaBody consultaBody) {
        return clienteService.buscar(consultaBody);
    }


    @CrossOrigin(origins = {API_URL, API_URL_LOCAL})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = {"/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity retornaCliente(@PathVariable Long id) {
        return clienteService.findById(id);
    }


    @CrossOrigin(origins = {API_URL, API_URL_LOCAL})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity novo(@RequestBody @Valid Client client) {
        return clienteService.novo(client);
    }


    @CrossOrigin(origins = {API_URL, API_URL_LOCAL})
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = {"/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return clienteService.deletar(id);
    }


    @CrossOrigin(origins = {API_URL, API_URL_LOCAL})
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = {"/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Client client) {
        return clienteService.atualizar(id, client);
    }

}
