package itsgnegrao.ProjectSpringBoot.service.Client;

import com.google.gson.Gson;
import itsgnegrao.ProjectSpringBoot.Utils.*;
import itsgnegrao.ProjectSpringBoot.models.Client;
import itsgnegrao.ProjectSpringBoot.models.ConsultaBody;
import itsgnegrao.ProjectSpringBoot.repository.Client.ClientRepository;
import itsgnegrao.ProjectSpringBoot.models.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.*;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    Gson gson;

    public ResponseEntity findById(Long id) {
        try {
            Optional<Client> cliente = clientRepository.findById(id);

            if (!cliente.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(false, "Cliente Não Encontrado")));
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
                    arr = clientRepository.findByCpfContaining(consultaBody.getValor());
                    break;
                case "nome":
                    arr = clientRepository.findByNomeContainingIgnoreCase(consultaBody.getValor());
                    break;
                case "sexo":
                    arr = clientRepository.findBySexo(consultaBody.getValor().charAt(0));
                    break;
                case "email":
                    arr = clientRepository.findByEmailContainingIgnoreCase(consultaBody.getValor());
                    break;
                case "naturalidade":
                    arr = clientRepository.findByNaturalidadeContainingIgnoreCase(consultaBody.getValor());
                    break;
                case "nacionalidade":
                    arr = clientRepository.findByNacionalidadeContainingIgnoreCase(consultaBody.getValor());
                    break;
                default:
                    arr = clientRepository.findAll();
                    ;
            }

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseBody(true, "Success", arr));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(new ResponseBody(false, "Fail")));
        }
    }

    public ResponseEntity novo(Client client) {
        try {
            //        Validação de Campos
            ArrayList<String> resp = validateClienteFields(client);
            if (!resp.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(false, "Fail", resp)));
            }

            client.setData_alt(new Timestamp(new Date().getTime()));
            client.setData_cad(new Timestamp(new Date().getTime()));
            try {
                Client cliente = clientRepository.save(client);
                return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(true, "Success", cliente)));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(false, "Fail", new ArrayList<String>(Arrays.asList("Cliente Provavelmente Já Cadastrado!")))));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(new ResponseBody(false, "Fail")));
        }
    }

    public ResponseEntity deletar(Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findById(id)
                    .map(record -> {
                        clientRepository.deleteById(id);
                        return new ResponseBody(true, "Success");
                    }).orElse(new ResponseBody(false, "Fail")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(new ResponseBody(false, "Fail")));
        }
    }

    public ResponseEntity atualizar(Long id, Client client) {
        try {
            Optional<Client> clienteOptional = clientRepository.findById(id);

            if (!clienteOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(false, "Cliente Não Encontrado")));
            }

            // Validação de Campos
            ArrayList<String> resp = validateClienteFields(client);
            if (!resp.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new ResponseBody(false, "Fail", resp)));
            }

            return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findById(id)
                    .map(record -> {
                        if (record.getCpf() != client.getCpf()) {
                            client.setData_alt(new Timestamp(new Date().getTime()));
                            client.setData_cad(record.getData_cad());
                            clientRepository.deleteById(id);
                            Client cliente = clientRepository.save(client);
                        } else {
                            record.setEmail(client.getEmail());
                            record.setNome(client.getNome());
                            record.setSexo(client.getSexo());
                            record.setData_nasc(client.getData_nasc());
                            record.setNaturalidade(client.getNaturalidade());
                            record.setNacionalidade(client.getNacionalidade());
                            record.setData_alt(new Timestamp(new Date().getTime()));
                            Client cliente = clientRepository.save(record);
                        }
                        return new ResponseBody(true, "Success");
                    }).orElse(new ResponseBody(false, "Fail")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson(new ResponseBody(false, "Fail")));
        }
    }

    private ArrayList<String> validateClienteFields(Client client) {
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
