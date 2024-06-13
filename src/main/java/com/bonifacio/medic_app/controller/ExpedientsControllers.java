package com.bonifacio.medic_app.controller;

import com.bonifacio.medic_app.controller.dtos.expedient.ExpedientRequest;
import com.bonifacio.medic_app.responses.Response;
import com.bonifacio.medic_app.services.expedients.IExpedientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/expedients/")
@AllArgsConstructor
public class ExpedientsControllers {
    @Autowired
    private final IExpedientService expedientService;

    @GetMapping(value = "")
    public ResponseEntity<Response<?>> index(@PageableDefault(page = 0,size = 10) Pageable pageable){
        var response = this.expedientService.getAll(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "")
    public ResponseEntity<Response<?>> store(@Valid @RequestBody ExpedientRequest expedientRequest,
                                             BindingResult result){
        try{
            if(result.hasErrors()) return new ResponseEntity<>(Response.builder()
                    .message("Error de Validaciones")
                    .status(false)
                    .data(result.getAllErrors())
                    .build(),HttpStatus.BAD_REQUEST);

            var response = this.expedientService.save(expedientRequest);
            if(!response.isStatus()) return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Response.builder()
                    .message(e.getMessage())
                    .status(false)
                    .data(e)
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "{id}/")
    public ResponseEntity<Response<?>> show(@PathVariable UUID id){
        var response = this.expedientService.findById(id);
        if(!response.isStatus()) new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
