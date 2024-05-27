package com.bonifacio.medic_app.controller;

import com.bonifacio.medic_app.responses.Response;
import com.bonifacio.medic_app.services.familiar.IFamiliarService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
