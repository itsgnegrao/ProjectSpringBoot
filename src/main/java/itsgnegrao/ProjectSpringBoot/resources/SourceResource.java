package itsgnegrao.ProjectSpringBoot.resources;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/source")
public class SourceResource {

    @CrossOrigin(origins = "*")
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String source() {
        return "<a href=\"https://github.com/itsgnegrao/ProjectSpringBoot\"> 1. Link do Projeto Back-End Java Spring Boot no Github. <a> " +
                " <br/><br/>" +
                "<a href=\"https://github.com/itsgnegrao/ProjectReact\"> 2. Link do Projeto Front-End ReactJs no Github. <a> " +
                " <br/><br/>" +
                "<a href=\"https://hub.docker.com/r/itsgnegrao/itsgnegrao-spring-boot\"> 3. Link do Projeto Back-End Java Spring Boot no Docker Hub. <a> " +
                " <br/><br/>" +
                "<a href=\"https://hub.docker.com/repository/docker/itsgnegrao/itsgnegrao-react-app\"> 4. Link do Projeto Front-End ReactJs no Docker Hub. <a> " +
                " <br/><br/>" +
                "<a href=\"https://itsgnegrao-spring-boot.herokuapp.com/\"> 5. Link do Projeto Back-End Java Spring Boot no Heroku. <a> " +
                " <br/><br/>" +
                "<a href=\"https://itsgnegrao-react-app.herokuapp.com/\"> 6. Link do Projeto Front-End ReactJs no Heroku. <a> ";
    }

}
