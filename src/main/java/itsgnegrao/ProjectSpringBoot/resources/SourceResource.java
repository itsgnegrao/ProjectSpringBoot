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
        return "<a href=\"https://github.com/itsgnegrao/ProjectSpringBoot\"> Link do Projeto no Github. <a>";
    }

}
