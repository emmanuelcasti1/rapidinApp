package com.rapidin.rapidinApp.controller;

import com.rapidin.rapidinApp.dto.RapidinDTO;
import com.rapidin.rapidinApp.model.Rapidin;
import com.rapidin.rapidinApp.service.IRapidinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rapidines")
public class RapidinController {

    @Autowired
    private IRapidinService rapidinService;

    @GetMapping("/{fecha}")
    public ResponseEntity<List<RapidinDTO>> rapidinesPorFechaEspecifica(@PathVariable LocalDate fecha) {
        List<Rapidin> rapidines = rapidinService.obtenerRapidinPorDiaEspecifico(fecha);
        if (rapidines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<RapidinDTO> rapidinDTOS = rapidines.stream().map(rapidinService::convertirRapidinDTO).toList();
        return ResponseEntity.ok(rapidinDTOS);
    }

    @GetMapping("/generales")
    public ResponseEntity<List<RapidinDTO>> rapidines() {
        List<Rapidin> rapidines = rapidinService.obtenerRapidines();
        if (rapidines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<RapidinDTO> rapidinDTOS = rapidines.stream().map(rapidinService::convertirRapidinDTO).toList();
        return ResponseEntity.ok(rapidinDTOS);
    }

    @GetMapping("/hoy")
    public ResponseEntity<List<RapidinDTO>> rapidinesActuales() {
        List<Rapidin> rapidines = rapidinService.obtenerRapidinesPorDiaActual();

        if (rapidines.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<RapidinDTO> rapidinDTOS = rapidines.stream()
                .map(rapidinService::convertirRapidinDTO)
                .toList();

        return ResponseEntity.ok(rapidinDTOS);

    }


    @PostMapping
    public ResponseEntity<String> resetearRapidines(){
        rapidinService.resetearValores();
        return new ResponseEntity<>("Rapidines resetados correctamente", HttpStatus.OK);
    }

}
