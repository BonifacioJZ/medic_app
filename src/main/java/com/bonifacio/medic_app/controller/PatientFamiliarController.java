package com.bonifacio.medic_app.controller;

import com.bonifacio.medic_app.controller.dtos.familiar_patient.PatientFamiliarRequest;
import com.bonifacio.medic_app.responses.Response;
import com.bonifacio.medic_app.services.familiar_patient.IPatientFamiliarService;
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

@RestController
@RequestMapping(value = "api/v1/patient/familiar/")
@AllArgsConstructor
public class PatientFamiliarController {

    @Autowired
    private final IPatientFamiliarService patientFamiliarService;

    @PostMapping(value = "")
    public ResponseEntity<Response<?>> addFamiliar(@Valid @RequestBody PatientFamiliarRequest patientFamiliarRequest,
                                                   BindingResult result){
        try{
            if(result.hasErrors()) return  new ResponseEntity<>(Response.builder()
                    .message("Error de validaciones")
                    .data(result.getAllErrors())
                    .status(false)
                    .build(),HttpStatus.BAD_REQUEST);
            var response = this.patientFamiliarService.addFamiliar(patientFamiliarRequest);
            if(response.getData()==null) return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Response.builder()
                    .message(e.getMessage())
                    .data(e)
                    .status(false)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
