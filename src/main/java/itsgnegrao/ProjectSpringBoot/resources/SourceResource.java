package itsgnegrao.ProjectSpringBoot.resources;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/source")
public class SourceResource {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String source() {
        return "<a href=\"https://github.com/itsgnegrao/ProjectSpringBoot\"> 1. Link do Projeto Back-End Java Spring Boot no Github. <a> " +
                " <br/>" +
                "<a href=\"https://github.com/itsgnegrao/ProjectReact\"> 2. Link do Projeto Front-End ReactJs no Github. <a> ";
    }

}
