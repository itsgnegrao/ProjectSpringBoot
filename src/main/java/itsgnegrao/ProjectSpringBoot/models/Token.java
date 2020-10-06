package itsgnegrao.ProjectSpringBoot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Token implements Serializable {

    private String email;

    private String token;

    private Boolean  auth = true;

    private Integer expires = 60;

    public Token(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
