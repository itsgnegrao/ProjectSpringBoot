package itsgnegrao.ProjectSpringBoot.resources.api;

import com.google.gson.Gson;
import itsgnegrao.ProjectSpringBoot.models.Token;
import itsgnegrao.ProjectSpringBoot.models.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/login")
public class LoginResource {

    @Autowired
    private Gson gson;

    @CrossOrigin(origins = "*")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity login(@RequestBody User user) {

        if(user.getUsername() == null || user.getUsername() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else if(user.getPassword().equals("") || user.getUsername().equals("")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String auth = user.getUsername() + ":" + user.getPassword();
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String tk = "Basic " + new String(encodedAuth);
        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(new Token(user.getUsername(), tk)));
    }

}
