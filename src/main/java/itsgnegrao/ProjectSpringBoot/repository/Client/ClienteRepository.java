package itsgnegrao.ProjectSpringBoot.repository.Client;

import itsgnegrao.ProjectSpringBoot.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByCpfContaining(String cpf);

    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    List<Cliente> findBySexo(Character sexo);

    List<Cliente> findByEmailContainingIgnoreCase(String email);

    List<Cliente> findByNaturalidadeContainingIgnoreCase(String naturalidade);

    List<Cliente> findByNacionalidadeContainingIgnoreCase(String nacionalidade);

}