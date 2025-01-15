package com.rapidin.rapidinApp.service;

import com.rapidin.rapidinApp.model.Domiciliario;
import com.rapidin.rapidinApp.model.Domicilio;

import java.time.LocalDate;
import java.util.List;

public interface IDomicilioService {
    void crearDomicilio(Domicilio domicilio);
    Domicilio buscarDomicilioPorId(Long id);
    void actualizarDomicilio(Long idDomicilio, String nombreNegocioNuevo, Double precioDomicilioNuevo,
                             LocalDate fechaDomicilioNueva, Domiciliario domiciliarioNuevo);
    List<Domicilio> Domicilios(LocalDate fecha);
    void eliminarDomicilio(Long idDomicilio);
}
