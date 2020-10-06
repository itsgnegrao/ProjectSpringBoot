package itsgnegrao.ProjectSpringBoot.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@Table(name="Client")
public class Cliente {

    @Id
    private String cpf;

    private String nome;

    private String email;

    private char sexo;

    private Date data_nasc;

    private String naturalidade;

    private String nacionalidade;

    private Timestamp data_cad;

    private Timestamp data_alt;

    public Cliente(String cpf, String nome, String email, char sexo, Date data_nasc, String naturalidade, String nacionalidade, Timestamp data_cad, Timestamp data_alt) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.sexo = sexo;
        this.data_nasc = data_nasc;
        this.naturalidade = naturalidade;
        this.nacionalidade = nacionalidade;
        this.data_cad = data_cad;
        this.data_alt = data_alt;
    }

    public Cliente() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Timestamp getData_cad() {
        return data_cad;
    }

    public void setData_cad(Timestamp data_cad) {
        this.data_cad = data_cad;
    }

    public Timestamp getData_alt() {
        return data_alt;
    }

    public void setData_alt(Timestamp data_alt) {
        this.data_alt = data_alt;
    }
}