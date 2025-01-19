package com.rapidin.rapidinApp.service;

import com.rapidin.rapidinApp.dto.RapidinDTO;
import com.rapidin.rapidinApp.model.Rapidin;

import java.time.LocalDate;
import java.util.List;

public interface IRapidinService {
    List<Rapidin> obtenerRapidinPorDiaEspecifico(LocalDate fecha);
    List<Rapidin> obtenerRapidines();
    List<Rapidin> obtenerRapidinesPorDiaActual();
    void resetearValores();
    RapidinDTO convertirRapidinDTO(Rapidin rapidin);

}
