package com.bonifacio.medic_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/patient/")
public class PatientController {


    @GetMapping(value = {"","/"})
    public ResponseEntity<?> index(){
        return new ResponseEntity<>("funciona", HttpStatus.OK);
    }
}
