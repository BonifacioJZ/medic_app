package com.bonifacio.medic_app.controller;

import com.bonifacio.medic_app.controller.dtos.familiar.FamiliarRequest;
import com.bonifacio.medic_app.responses.Response;
import com.bonifacio.medic_app.services.familiar.IFamiliarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/familiar/")
@AllArgsConstructor
public class FamiliarController {
    @Autowired
    private final IFamiliarService familiarService;

    @GetMapping(value = "")
    public ResponseEntity<Response<?>> index(@PageableDefault(page = 0,size = 10) Pageable pageable){
        var response = this.familiarService.getAll(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = "")
    public ResponseEntity<Response<?>> store(@Valid @RequestBody FamiliarRequest request, BindingResult result){
        try{
            if(result.hasErrors()) return new ResponseEntity<>(Response.builder()
                    .message("Error Validaciones")
                    .data(result.getAllErrors())
                    .status(false)
                    .build(),HttpStatus.BAD_REQUEST);
            var response = this.familiarService.save(request);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(Response.builder()
                    .message("Error")
                    .data(e.getMessage())
                    .status(false)
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "{curp}/")
    public ResponseEntity<Response<?>> show(@PathVariable String curp){
        var response = this.familiarService.show(curp);
        if(response.getData() ==null) return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
