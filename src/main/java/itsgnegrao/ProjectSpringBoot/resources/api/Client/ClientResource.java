package itsgnegrao.ProjectSpringBoot.resources.api.Client;

import itsgnegrao.ProjectSpringBoot.Utils.*;
import itsgnegrao.ProjectSpringBoot.models.Cliente;
import com.google.gson.Gson;
import itsgnegrao.ProjectSpringBoot.service.Client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import itsgnegrao.ProjectSpringBoot.resources.api.ResponseBody;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientResource {
    @Autowired
    ClientService clienteService;

    @Autowired
    Gson gson;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "Consulta o cadastro do Cliente permitindo passar filtros e paginação", response = List.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Retorno padrão, nele virá uma lista com os Clientes, total de itens no retorno e total de itens geral", response = CadCliente.class),
//            @ApiResponse(code = 500, message = "Erro inesperado no VipTechServices! ")
//    })
    public ResponseEntity retornaClientes() {
        System.out.println("\n\nCOLOCA PAGINAÇÃO AQUII!!\n\n");
        System.out.printf(gson.toJson(clienteService.retornaAll()));
        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(clienteService.retornaAll()));

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity novo(@RequestBody @Valid Cliente client) {
//        Validação de Campos
        ArrayList<String> resp = validateClienteFields(client);
        if(!resp.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(false, "Fail", resp)));
        }

        client.setData_alt(new Timestamp(new Date().getTime()));
        client.setData_cad(new Timestamp(new Date().getTime()));
        try{
            Cliente cliente = clienteService.save(client);
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(true, "Success", cliente)));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(false, "Fail")));
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path ={"/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <?> delete(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id)
                .map(record -> {
                    clienteService.deleteById(id);
                    return new ResponseBody(true, "Success");
                }).orElse(new ResponseBody(false, "Fail")));
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path ={"/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <?> put(@PathVariable long id, @RequestBody Cliente client) {
        Optional<Cliente> clienteOptional = clienteService.findById(id);

        if (!clienteOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(new ResponseBody(false, "Cliente Não Encontrado")));
        }

        // Validação de Campos
        ArrayList<String> resp = validateClienteFields(client);
        if(!resp.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(false, "Fail", resp)));
        }

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id)
                .map(record -> {
                    if(record.getCpf() != client.getCpf()){
                        client.setData_alt(new Timestamp(new Date().getTime()));
                        client.setData_cad(record.getData_cad());
                        clienteService.deleteById(id);
                        Cliente cliente = clienteService.save(client);
                    }
                    else{
                        record.setEmail(client.getEmail());
                        record.setNome(client.getNome());
                        record.setSexo(client.getSexo());
                        record.setData_nasc(client.getData_nasc());
                        record.setNaturalidade(client.getNaturalidade());
                        record.setNacionalidade(client.getNacionalidade());
                        record.setData_alt(new Timestamp(new Date().getTime()));
                        Cliente cliente = clienteService.save(record);
                    }
                    return new ResponseBody(true, "Success");
                }).orElse(new ResponseBody(false, "Fail")));

    }

    private ArrayList<String> validateClienteFields(Cliente client) {
        ArrayList<String> arr = new ArrayList<>();

        if(!CpfCnpjUtils.isValid(client.getCpf())){
            arr.add("CPF Inválido, digite um CPF Válido!");
        }
        if(!DataUtils.isValid(client.getData_nasc())){
            arr.add("Data de Nascimento Inválida, digite uma Data Válida!");
        }
        if(!EmailUtils.isValid(client.getEmail())){
            arr.add("Email Inválido, digite um Email Válido!");
        }

        return arr;
    }

}
