package com.bonifacio.medic_app.controller;

import com.bonifacio.medic_app.controller.dtos.familiar_patient.FamiliarPatientRequest;
import com.bonifacio.medic_app.responses.Response;
import com.bonifacio.medic_app.services.familiar_patient.IFamiliarPatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/v1/patient_familiar")
public class FamiliarPatientController {
    @Autowired
    private final IFamiliarPatientService familiarPatientService;

    @PostMapping(value = "")
    public ResponseEntity<Response<?>> add(@Valid @RequestBody FamiliarPatientRequest familiarPatientRequest
            , BindingResult result){
        try{
            if(result.hasErrors()) return new ResponseEntity<>(Response.builder()
                    .data(result.getAllErrors())
                    .message("Error Validacion")
                    .status(true)
                    .build(),HttpStatus.BAD_REQUEST);
            var response = this.familiarPatientService.addPatients(familiarPatientRequest);
            if (response.getData()==null) return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Response.builder()
                    .message("Error")
                    .data(e)
                    .status(false)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
