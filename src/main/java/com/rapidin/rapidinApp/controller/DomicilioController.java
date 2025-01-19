package com.rapidin.rapidinApp.controller;

import com.rapidin.rapidinApp.dto.DomicilioDTO;
import com.rapidin.rapidinApp.model.Domicilio;
import com.rapidin.rapidinApp.service.IDomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {

    @Autowired
    private IDomicilioService domicilioService;

    @PostMapping
    public ResponseEntity<String> crearDomicilio(@RequestBody DomicilioDTO domicilio) {
        domicilioService.crearDomicilio(domicilio);
        return ResponseEntity.ok("Domicilio creado correctamente");
    }

    @GetMapping
    public ResponseEntity<List<Domicilio>> domicilios() {
        List<Domicilio> domicilios = domicilioService.domicilios();
        return ResponseEntity.ok(domicilios);
    }


    @GetMapping("/PorId/{idDomicilio}")
    public ResponseEntity<Domicilio> buscarDomicilioPorId(@PathVariable Long idDomicilio) {
        Domicilio domicilio = domicilioService.buscarDomicilioPorId(idDomicilio);
        return ResponseEntity.ok(domicilio);
    }

    @GetMapping("/PorFecha/{fechaDomicilio}")
    public ResponseEntity<List<Domicilio>> listarDomicilios(@PathVariable LocalDate fechaDomicilio) {
        List<Domicilio> domicilios = domicilioService.domiciliosPorFecha(fechaDomicilio);
        return ResponseEntity.ok(domicilios);
    }

    @PatchMapping("/{idDomicilio}")
    public ResponseEntity<String> editarDomicilio(@RequestBody DomicilioDTO domicilio, @PathVariable Long idDomicilio) {
        domicilioService.actualizarDomicilio(idDomicilio, domicilio);
        return ResponseEntity.ok("Domicilio actualizado correctamente");
    }

    @DeleteMapping("/{idDomicilio}")
    public ResponseEntity<String> eliminarDomicilio(@PathVariable Long idDomicilio) {
        domicilioService.eliminarDomicilio(idDomicilio);
        return ResponseEntity.ok("Domicilio eliminado correctamente");
    }


}
