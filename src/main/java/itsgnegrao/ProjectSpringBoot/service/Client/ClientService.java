package itsgnegrao.ProjectSpringBoot.service.Client;

import com.google.gson.Gson;
import itsgnegrao.ProjectSpringBoot.Utils.*;
import itsgnegrao.ProjectSpringBoot.models.Cliente;
import itsgnegrao.ProjectSpringBoot.models.ConsultaBody;
import itsgnegrao.ProjectSpringBoot.repository.Client.ClienteRepository;
import itsgnegrao.ProjectSpringBoot.resources.api.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    Gson gson;

    public ResponseEntity findById(Long id) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);

            if (!cliente.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(new ResponseBody(false, "Cliente Não Encontrado")));
            }

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseBody(true, "Success", cliente.get()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(new ResponseBody(false, "Fail")));
        }
    }

    public ResponseEntity buscar(ConsultaBody consultaBody) {
        try {
            List arr;

            switch (consultaBody.getCampo().toLowerCase().trim()) {
                case "cpf":
                    arr = clienteRepository.findByCpfContaining(consultaBody.getValor());
                    break;
                case "nome":
                    arr = clienteRepository.findByNomeContainingIgnoreCase(consultaBody.getValor());
                    break;
                case "sexo":
                    arr = clienteRepository.findBySexo(consultaBody.getValor().charAt(0));
                    break;
                case "email":
                    arr = clienteRepository.findByEmailContainingIgnoreCase(consultaBody.getValor());
                    break;
                case "naturalidade":
                    arr = clienteRepository.findByNaturalidadeContainingIgnoreCase(consultaBody.getValor());
                    break;
                case "nacionalidade":
                    arr = clienteRepository.findByNacionalidadeContainingIgnoreCase(consultaBody.getValor());
                    break;
                default:
                    arr = clienteRepository.findAll();
                    ;
            }

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseBody(true, "Success", arr));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(new ResponseBody(false, "Fail")));
        }
    }

    public ResponseEntity novo(Cliente client) {
        try {
            //        Validação de Campos
            ArrayList<String> resp = validateClienteFields(client);
            if (!resp.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(false, "Fail", resp)));
            }

            client.setData_alt(new Timestamp(new Date().getTime()));
            client.setData_cad(new Timestamp(new Date().getTime()));
            try {
                Cliente cliente = clienteRepository.save(client);
                return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(true, "Success", cliente)));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(false, "Fail")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(false, "Fail")));
        }
    }

    public ResponseEntity deletar(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findById(id)
                    .map(record -> {
                        clienteRepository.deleteById(id);
                        return new ResponseBody(true, "Success");
                    }).orElse(new ResponseBody(false, "Fail")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(false, "Fail")));
        }
    }

    public ResponseEntity atualizar(Long id, Cliente client) {
        try {
            Optional<Cliente> clienteOptional = clienteRepository.findById(id);

            if (!clienteOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(new ResponseBody(false, "Cliente Não Encontrado")));
            }

            // Validação de Campos
            ArrayList<String> resp = validateClienteFields(client);
            if (!resp.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(false, "Fail", resp)));
            }

            return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findById(id)
                    .map(record -> {
                        if (record.getCpf() != client.getCpf()) {
                            client.setData_alt(new Timestamp(new Date().getTime()));
                            client.setData_cad(record.getData_cad());
                            clienteRepository.deleteById(id);
                            Cliente cliente = clienteRepository.save(client);
                        } else {
                            record.setEmail(client.getEmail());
                            record.setNome(client.getNome());
                            record.setSexo(client.getSexo());
                            record.setData_nasc(client.getData_nasc());
                            record.setNaturalidade(client.getNaturalidade());
                            record.setNacionalidade(client.getNacionalidade());
                            record.setData_alt(new Timestamp(new Date().getTime()));
                            Cliente cliente = clienteRepository.save(record);
                        }
                        return new ResponseBody(true, "Success");
                    }).orElse(new ResponseBody(false, "Fail")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(false, "Fail")));
        }
    }

    private ArrayList<String> validateClienteFields(Cliente client) {
        ArrayList<String> arr = new ArrayList<>();

        if (!CpfCnpjUtils.isValid(client.getCpf())) {
            arr.add("CPF Inválido, digite um CPF Válido!");
        }
        if (!DataUtils.isValid(client.getData_nasc())) {
            arr.add("Data de Nascimento Inválida, digite uma Data Válida!");
        }
        if (!EmailUtils.isValid(client.getEmail())) {
            arr.add("Email Inválido, digite um Email Válido!");
        }

        return arr;
    }

}
