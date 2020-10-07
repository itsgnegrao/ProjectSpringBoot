package itsgnegrao.ProjectSpringBoot.resources.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseBody implements Serializable {

    private boolean success;

    private String message;

    private Object content;

    public ResponseBody(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseBody(boolean success, String message, Object content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }
}
