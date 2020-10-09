package itsgnegrao.ProjectSpringBoot.resources.api;

import io.restassured.http.ContentType;
import itsgnegrao.ProjectSpringBoot.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LoginResourceTests {

    @Autowired
    private LoginResource loginResource;

    @BeforeEach
    public void setup(){
        standaloneSetup(this.loginResource);
    }

    @Test
    void notNull() {
        assertThat(loginResource).isNotNull();
    }

    @Test
    public void deveRetornarOK() throws Exception {
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(new User("123","123")).when().post ("/api/login").then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarBadRequest() throws Exception {
        given().contentType(ContentType.JSON).body(new User()).when().post ("/api/login").then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void deveRetornarNotFound() throws Exception {
        given().contentType(ContentType.JSON).body(new User("123","")).when().post ("/api/login").then().statusCode(HttpStatus.NOT_FOUND.value());
        given().contentType(ContentType.JSON).body(new User("","123")).when().post ("/api/login").then().statusCode(HttpStatus.NOT_FOUND.value());
    }
}
