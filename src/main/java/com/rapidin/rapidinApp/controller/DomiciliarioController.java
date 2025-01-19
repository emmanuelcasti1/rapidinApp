package com.rapidin.rapidinApp.controller;

import com.rapidin.rapidinApp.model.Domiciliario;
import com.rapidin.rapidinApp.service.IDomiciliarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domiciliarios")
public class DomiciliarioController {

    @Autowired
    private IDomiciliarioService domiciliarioService;

    @PostMapping
    public ResponseEntity<String> crearDomiciliario(@RequestBody Domiciliario domiciliario) {
        domiciliarioService.crearDomiciliario(domiciliario);
        return ResponseEntity.ok("Domiciliario creado correctamente");
    }

    @GetMapping("/cedula/{cedulaDomiciliario}")
    public ResponseEntity<Domiciliario> buscarDomiciliarioPorCedula(@PathVariable String cedulaDomiciliario) {
        Domiciliario domiciliario = domiciliarioService.buscarDomiciliarioPorCedula(cedulaDomiciliario);
        return ResponseEntity.ok(domiciliario);
    }

    @GetMapping("/id/{idDomiciliario}")
    public ResponseEntity<Domiciliario> buscarDomiciliarioPorCedula(@PathVariable Long idDomiciliario) {
        Domiciliario domiciliario = domiciliarioService.buscarDomiciliarioPorId(idDomiciliario);
        return ResponseEntity.ok(domiciliario);
    }

    @GetMapping
    public ResponseEntity<List<Domiciliario>> listarDomiciliarios() {
        List<Domiciliario> domiciliarios = domiciliarioService.Domiciliarios();
        return ResponseEntity.ok(domiciliarios);
    }

    @PatchMapping("/{cedulaDomiciliario}")
    public ResponseEntity<String> editarDomiciliario(@PathVariable String cedulaDomiciliario,
                                                     @RequestBody Domiciliario editado) {
        domiciliarioService.actualizarDomiciliario(cedulaDomiciliario, editado);
        return ResponseEntity.ok("Domiciliario editado correctamente");
    }

    @DeleteMapping("/{cedulaDomiciliario}")
    public ResponseEntity<String> eliminarDomiciliario(@PathVariable String cedulaDomiciliario) {
        domiciliarioService.eliminarDomiciliario(cedulaDomiciliario);
        return ResponseEntity.ok("Domiciliario eliminado correctamente");
    }

    @PostMapping("/resetearTotal")
    public ResponseEntity<String> resetearTotal() {
        domiciliarioService.resetearTotalDomiciliarios();
        return ResponseEntity.ok("Totales de domiciliarios reseteados correctamente");
    }
}
