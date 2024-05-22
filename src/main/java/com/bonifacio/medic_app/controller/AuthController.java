package com.bonifacio.medic_app.controller;

import com.bonifacio.medic_app.controller.dtos.auth.AuthRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.bonifacio.medic_app.controller.dtos.auth.AuthCreateUserRequest;
import com.bonifacio.medic_app.responses.AuthRespose;
import com.bonifacio.medic_app.services.UserDetailsImplement;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RequestMapping(value = "api/v1/auth/")
@RestController
@AllArgsConstructor
public class AuthController {    

    private final UserDetailsImplement userService;

    @PostMapping(value = {"register/"})
    public ResponseEntity<AuthRespose> register(@Valid @RequestBody AuthCreateUserRequest user,
                            BindingResult result){
        try {
            if(result.hasErrors()){
                throw new IllegalArgumentException(result.getAllErrors().toString());
            }
            AuthRespose response = userService.registerUser(user);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    @PostMapping(value = {"login/"})
    public ResponseEntity<AuthRespose> login(@Valid @RequestBody AuthRequest login,BindingResult result){
        try{
            if(result.hasErrors()) throw  new IllegalArgumentException(result.getAllErrors().toString());
            AuthRespose respose = userService.loginUser(login);
            return new ResponseEntity<>(respose,HttpStatus.OK);
        }catch (Exception e){
            throw  new IllegalArgumentException(e.getMessage());
        }
    }
}