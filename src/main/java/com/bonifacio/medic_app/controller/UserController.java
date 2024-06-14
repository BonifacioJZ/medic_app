package com.bonifacio.medic_app.controller;

import com.bonifacio.medic_app.responses.Response;
import com.bonifacio.medic_app.services.user.UserDetailsImplement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/user/")
@AllArgsConstructor
public class UserController {


    private final UserDetailsImplement userDetailsImplement;

    @GetMapping(value = "{username}/")
    public ResponseEntity<Response<?>> show(@PathVariable String username){

        var response = this.userDetailsImplement.getUserByUsername(username);

        if(!response.isStatus()) return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping(value = "profile/")
    public ResponseEntity<Response<?>> profile(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer){
        var response = this.userDetailsImplement.getUserByToken(bearer.substring(7));

        if(!response.isStatus()) return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
