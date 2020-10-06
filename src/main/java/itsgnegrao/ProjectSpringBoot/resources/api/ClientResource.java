package itsgnegrao.ProjectSpringBoot.resources.api;

import com.google.gson.Gson;
import itsgnegrao.ProjectSpringBoot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientResource {
    @Autowired
    ClientService clienteService;

    @Autowired
    Gson gson;

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Consulta o cadastro do Cliente permitindo passar filtros e paginação", response = List.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Retorno padrão, nele virá uma lista com os Clientes, total de itens no retorno e total de itens geral", response = CadCliente.class),
//            @ApiResponse(code = 500, message = "Erro inesperado no VipTechServices! ")
//    })
    public ResponseEntity retornaClientes() {
        System.out.printf(gson.toJson(clienteService.retornaAll()));
        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(clienteService.retornaAll()));

    }
}
