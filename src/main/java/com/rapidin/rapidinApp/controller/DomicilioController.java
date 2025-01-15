package com.rapidin.rapidinApp.controller;

import com.rapidin.rapidinApp.model.Domicilio;
import com.rapidin.rapidinApp.service.IDomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    @Autowired
    private IDomicilioService domicilioService;

    @PostMapping
    public ResponseEntity<String> crearDomicilio(@RequestBody Domicilio domicilio) {
        domicilioService.crearDomicilio(domicilio);
        return ResponseEntity.ok("Domicilio creado correctamente");
    }

}
