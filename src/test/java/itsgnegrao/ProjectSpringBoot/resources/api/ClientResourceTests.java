package itsgnegrao.ProjectSpringBoot.resources.api;

import io.restassured.http.ContentType;
import itsgnegrao.ProjectSpringBoot.models.Client;
import itsgnegrao.ProjectSpringBoot.models.ConsultaBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.sql.Date;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClientResourceTests {
    
    @Autowired
    private ClientResource clientResource;

    @BeforeEach
    public void setup(){
        standaloneSetup(this.clientResource);
    }

    @Test
    void notNull() {
        assertThat(clientResource).isNotNull();
    }


//  Buscar
    @Test
    public void buscar_deveRetornarBadRequest() throws Exception {
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new ConsultaBody()).when().post ("/api/client/get").then().statusCode(HttpStatus.BAD_REQUEST.value());
        given().contentType(ContentType.JSON).accept(ContentType.JSON).when().post ("/api/client/get").then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void buscar_deveRetornarOK() throws Exception {
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new ConsultaBody("nome", "testerson")).when().post ("/api/client/get").then().statusCode(HttpStatus.OK.value());
    }


//  Retorna Cliente
    @Test
    public void retornaCliente_deveRetornarOK() throws Exception {
        given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get ("/api/client/1").then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void retornaCliente_deveRetornarNotFound() throws Exception {
        given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get ("/api/client/2").then().statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void retornaCliente_deveRetornarMethodNotAllowed() throws Exception {
        given().contentType(ContentType.JSON).accept(ContentType.JSON).when().get ("/api/client").then().statusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
    }


//  Deletar
    @Test
    public void deletar_deveRetornarOk() throws Exception {
        given().accept(ContentType.JSON).when().delete ("/api/client/1").then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deletar_deveRetornarBadRequest() throws Exception {
        given().accept(ContentType.JSON).when().delete ("/api/client/a").then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void deletar_deveRetornarMethodNotAllowed() throws Exception {
        given().accept(ContentType.JSON).when().delete ("/api/client").then().statusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
    }


//  Atualizar
    @Test
    public void atualizar_deveRetornarOk() throws Exception {
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new Client()).when().put ("/api/client/1").then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void atualizar_deveRetornarUnsuportedMediaType() throws Exception {
        given().accept(ContentType.JSON).body(new Client()).when().put ("/api/client/1").then().statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

    @Test
    public void atualizar_deveRetornarAccepted() throws Exception {
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new Client("7614542", "teste", Date.valueOf("1996-12-10"), "testerson@teste.com")).when().put  ("/api/client/1").then().statusCode(HttpStatus.ACCEPTED.value());
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new Client("76145420022", "teste", Date.valueOf("2021-12-10"), "testerson@teste.com")).when().put ("/api/client/1").then().statusCode(HttpStatus.ACCEPTED.value());
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new Client("76145420022", "teste", Date.valueOf("2021-12-10"), "testerson")).when().put ("/api/client/1").then().statusCode(HttpStatus.ACCEPTED.value());
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new Client()).when().put ("/api/client/2").then().statusCode(HttpStatus.ACCEPTED.value());
    }

    @Test
    public void atualizar_deveRetornarMethodNotAllowed() throws Exception {
        given().accept(ContentType.JSON).body(new Client()).when().put ("/api/client").then().statusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
    }


//  Inserir
    @Test
    public void novo_deveRetornarOK() throws Exception {
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new Client("76145420022", "teste", Date.valueOf("1996-12-10"), "testerson@teste.com")).when().post ("/api/client").then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void novo_deveRetornarBadRequest() throws Exception {
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new Client("", "teste", Date.valueOf("1996-12-10"), "testerson@teste.com")).when().post ("/api/client").then().statusCode(HttpStatus.BAD_REQUEST.value());
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new Client("76145420022", "", Date.valueOf("1996-12-10"), "testerson@teste.com")).when().post ("/api/client").then().statusCode(HttpStatus.BAD_REQUEST.value());
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new Client("76145420022", "teste", null, "testerson@teste.com")).when().post ("/api/client").then().statusCode(HttpStatus.BAD_REQUEST.value());
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new Client("76145420022", "teste", Date.valueOf("1996-12-10"), "")).when().post ("/api/client").then().statusCode(HttpStatus.BAD_REQUEST.value());
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new Client()).when().post ("/api/client").then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void novo_deveRetornarAccepted()  throws Exception {
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new Client("7614542", "teste", Date.valueOf("1996-12-10"), "testerson@teste.com")).when().post ("/api/client").then().statusCode(HttpStatus.ACCEPTED.value());
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new Client("76145420022", "teste", Date.valueOf("2021-12-10"), "testerson@teste.com")).when().post ("/api/client").then().statusCode(HttpStatus.ACCEPTED.value());
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new Client("76145420022", "teste", Date.valueOf("2021-12-10"), "testerson")).when().post ("/api/client").then().statusCode(HttpStatus.ACCEPTED.value());
    }

    @Test
    public void novo_deveRetornarUnsuportedMediaType() throws Exception {
        given().accept(ContentType.JSON).body(new Client()).when().post ("/api/client").then().statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

}
