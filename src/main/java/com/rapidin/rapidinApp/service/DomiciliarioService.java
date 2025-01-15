package com.rapidin.rapidinApp.service;

import com.rapidin.rapidinApp.model.Domiciliario;
import com.rapidin.rapidinApp.repository.IDomiciliarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomiciliarioService implements IDomiciliarioService {

    @Autowired
    private IDomiciliarioRepository domiciliarioRepository;

    @Override
    public void crearDomiciliario(Domiciliario domiciliario) {
        domiciliarioRepository.save(domiciliario);
    }

    @Override
    public Domiciliario buscarDomiciliarioPorCedula(String cedula) {
        return domiciliarioRepository.findDomiciliarioByCedula(cedula).orElseThrow(() ->
                new RuntimeException("Domiciliario no encontrado con cedula: " + cedula));
    }

    @Override
    public List<Domiciliario> Domiciliarios() {
        return domiciliarioRepository.findAll();
    }

    @Override
    public void actualizarDomiciliario(String cedula, String nuevoNombre, String nuevoTelefono, Double nuevoTotalDomicilios) {
        Domiciliario domiciliario = domiciliarioRepository.findDomiciliarioByCedula(cedula)
                .orElseThrow(() -> new RuntimeException("Domiciliario no encontrado con cédula: " + cedula));

        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            domiciliario.setNombreDomiciliario(nuevoNombre);
        }

        if (nuevoTelefono != null && !nuevoTelefono.isEmpty()) {
            domiciliario.setTelefono(nuevoTelefono);
        }

        if (nuevoTotalDomicilios != null) {
            domiciliario.setTotalDomicilios(nuevoTotalDomicilios);
        }

        domiciliarioRepository.save(domiciliario);
    }

    @Override
    public void eliminarDomiciliario(String cedula) {
        Domiciliario domiciliario = domiciliarioRepository.findDomiciliarioByCedula(cedula).orElseThrow(() ->
                new RuntimeException("Domiciliario no encontrado con cédula: " + cedula));
        domiciliarioRepository.delete(domiciliario);
    }

    @Override
    public void resetearTotalDomiciliarios() {
        List<Domiciliario> domiciliarios = domiciliarioRepository.findAll();
        for (Domiciliario domiciliario : domiciliarios) {
            domiciliario.setTotalDomicilios(0.0);
        }
        domiciliarioRepository.saveAll(domiciliarios);
    }
}
