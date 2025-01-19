package com.rapidin.rapidinApp.service;

import com.rapidin.rapidinApp.dto.DomicilioDTO;
import com.rapidin.rapidinApp.model.Domicilio;

import java.time.LocalDate;
import java.util.List;

public interface IDomicilioService {
    void crearDomicilio(DomicilioDTO domicilioDTOicilio);
    Domicilio buscarDomicilioPorId(Long id);
    void actualizarDomicilio(Long idDomicilio, DomicilioDTO domicilio);
    List<Domicilio> domiciliosPorFecha(LocalDate fecha);
    void eliminarDomicilio(Long idDomicilio);
    void actualizarRapidin(LocalDate fecha, Domicilio domicilio);
    List<Domicilio> domicilios();
}

