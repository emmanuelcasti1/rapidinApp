package com.rapidin.rapidinApp.service;

import com.rapidin.rapidinApp.model.Domiciliario;

import java.util.List;

public interface IDomiciliarioService {
    void crearDomiciliario(Domiciliario domiciliario);
    Domiciliario buscarDomiciliarioPorCedula(String cedula);
    Domiciliario buscarDomiciliarioPorId(Long idDomiciliario);
    List<Domiciliario> Domiciliarios();
    void actualizarDomiciliario(String cedula, Domiciliario domiciliario);
    void eliminarDomiciliario(String cedula);
    void resetearTotalDomiciliarios();
}
