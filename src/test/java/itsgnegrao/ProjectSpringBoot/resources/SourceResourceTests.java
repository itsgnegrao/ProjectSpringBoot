package itsgnegrao.ProjectSpringBoot.resources;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SourceResourceTests {

    @Autowired
    private SourceResource sourceResource;

    @BeforeEach
    public void setup(){
        standaloneSetup(this.sourceResource);
    }

    @Test
    void notNull() {
        assertThat(sourceResource).isNotNull();
    }

    @Test
    public void deveRetornarOK() throws Exception {
        given().accept(ContentType.HTML).when().get("/source").then().statusCode(HttpStatus.OK.value());
    }


}
