package itsgnegrao.ProjectSpringBoot.resources;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static itsgnegrao.ProjectSpringBoot.configs.ConfigsFrontEnd.API_URL;
import static itsgnegrao.ProjectSpringBoot.configs.ConfigsFrontEnd.API_URL_LOCAL;

@RestController
@RequestMapping("/source")
public class SourceResource {

    @CrossOrigin(origins = {API_URL, API_URL_LOCAL})
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String source() {
        return "<a href=\"https://github.com/itsgnegrao/ProjectSpringBoot\"> 1. Link do Projeto Back-End Java Spring Boot no Github. <a> " +
                " <br/>" +
                "<a href=\"https://github.com/itsgnegrao/ProjectReact\"> 2. Link do Projeto Front-End ReactJs no Github. <a> ";
    }

}
