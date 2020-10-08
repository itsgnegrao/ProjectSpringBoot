package itsgnegrao.ProjectSpringBoot.repository.Client;

import itsgnegrao.ProjectSpringBoot.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByCpfContaining(String cpf);

    List<Client> findByNomeContainingIgnoreCase(String nome);

    List<Client> findBySexo(Character sexo);

    List<Client> findByEmailContainingIgnoreCase(String email);

    List<Client> findByNaturalidadeContainingIgnoreCase(String naturalidade);

    List<Client> findByNacionalidadeContainingIgnoreCase(String nacionalidade);

}