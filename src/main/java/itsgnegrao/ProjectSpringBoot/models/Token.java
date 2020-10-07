package itsgnegrao.ProjectSpringBoot.models;

import java.io.Serializable;

public class Token implements Serializable {

    private String email;

    private String token;

    private Boolean  auth = true;

    private Integer expires = 60;

    public Token(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public Token() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }
}
