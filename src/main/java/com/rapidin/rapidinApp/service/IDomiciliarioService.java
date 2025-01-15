package com.rapidin.rapidinApp.service;

import com.rapidin.rapidinApp.model.Domiciliario;

import java.util.List;

public interface IDomiciliarioService {
    void crearDomiciliario(Domiciliario domiciliario);
    Domiciliario buscarDomiciliarioPorCedula(String cedula);
    List<Domiciliario> Domiciliarios();
    void actualizarDomiciliario(String cedula, String nuevoNombre, String nuevoTelefono, Double nuevoTotalDomicilios);
    void eliminarDomiciliario(String cedula);
    void resetearTotalDomiciliarios();
}
