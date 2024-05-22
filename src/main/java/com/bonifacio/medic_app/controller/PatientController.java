package com.bonifacio.medic_app.controller;

import com.bonifacio.medic_app.responses.Response;
import com.bonifacio.medic_app.services.patient.IPatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/patient/")
@AllArgsConstructor
public class PatientController {
    @Autowired
    private final IPatientService patientService;
    @GetMapping(value = {"","/"})
    public ResponseEntity<Response<?>> index(){
        var data = patientService.getAll();
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
}
