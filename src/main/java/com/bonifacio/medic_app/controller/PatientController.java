package com.bonifacio.medic_app.controller;

import com.bonifacio.medic_app.controller.dtos.patient.PatientDetailsResponse;
import com.bonifacio.medic_app.controller.dtos.patient.PatientRequest;
import com.bonifacio.medic_app.responses.Response;
import com.bonifacio.medic_app.services.patient.IPatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping(value = "api/v1/patient/")
@AllArgsConstructor
public class PatientController {
    @Autowired
    private final IPatientService patientService;
    @GetMapping(value = {"","/"})
    public ResponseEntity<Response<?>> index(@PageableDefault(page = 0,size = 10) Pageable pageable){
        try{
            var data = patientService.getAll(pageable);
            return new ResponseEntity<>(data,HttpStatus.OK);
        }catch (HttpServerErrorException e){
            return new ResponseEntity<>(Response.builder()
                    .message("Error")
                    .data(e.getMessage())
                    .status(false)
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = {"","/"})
    public ResponseEntity<Response<?>> store(@Valid @RequestBody PatientRequest patientRequest, BindingResult result){
        try{
            if(result.hasErrors())return new ResponseEntity<>(Response.builder()
                    .message("Error en las validaciones")
                    .status(false)
                    .data(result.getAllErrors())
                    .build(),HttpStatus.BAD_REQUEST);
            var response = this.patientService.save(patientRequest);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(Response.builder()
                    .message("error")
                    .data(e.getMessage())
                    .status(false)
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "{curp}/")
    public ResponseEntity<Response<?>> show(@PathVariable String curp){
        Response<PatientDetailsResponse> response = this.patientService.getByCurp(curp);
        if(response.getData()==null) return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping(value = "{curp}/")
    public ResponseEntity<Response<?>> edit(@PathVariable String curp,@Valid @RequestBody PatientRequest patientRequest, BindingResult result ){
        try{
            if(result.hasErrors()) return  new ResponseEntity<>(Response.builder()
                    .message("Error al Validar")
                    .status(false)
                    .data(result.getAllErrors())
                    .build(),HttpStatus.BAD_REQUEST);
            var response = this.patientService.update(curp,patientRequest);
            if (response.getData()==null) return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e){
          return new ResponseEntity<>(Response.builder()
                  .message("Error")
                  .status(false)
                  .data(e.getMessage())
                  .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = {"{curp}/"})
    public ResponseEntity<Response<?>> delete(@PathVariable String curp){
        this.patientService.delete(curp);
        return new ResponseEntity<>(Response.builder()
                .message("Eliminado")
                .data(1)
                .status(true)
                .build(),HttpStatus.OK);
    }
}


