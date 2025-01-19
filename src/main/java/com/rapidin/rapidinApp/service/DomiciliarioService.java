package com.rapidin.rapidinApp.service;

import com.rapidin.rapidinApp.model.Domiciliario;
import com.rapidin.rapidinApp.model.Rapidin;
import com.rapidin.rapidinApp.repository.IDomiciliarioRepository;
import com.rapidin.rapidinApp.repository.IRapidinRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DomiciliarioService implements IDomiciliarioService {

    @Autowired
    private IDomiciliarioRepository domiciliarioRepository;

    @Autowired
    private IRapidinRepository rapidinRepository;

    @Override
    public void crearDomiciliario(Domiciliario domiciliario) {
        try {
            domiciliarioRepository.save(domiciliario);
            Rapidin rapidin = new Rapidin();
            rapidin.setFecha(LocalDate.now());
            rapidin.setDomiciliario(domiciliario);
            rapidin.setTotalIngreso(0.0);
            rapidinRepository.save(rapidin);
        }catch (PersistenceException e){
            throw new RuntimeException("La cedula ya esta registrada en el sistema");
        }
    }

    @Override
    public Domiciliario buscarDomiciliarioPorCedula(String cedula) {
        return domiciliarioRepository.findDomiciliarioByCedula(cedula).orElseThrow(() ->
                new RuntimeException("Domiciliario no encontrado con cedula: " + cedula));
    }

    @Override
    public Domiciliario buscarDomiciliarioPorId(Long idDomiciliario) {
        return domiciliarioRepository.findById(idDomiciliario).orElseThrow(() -> new RuntimeException(
                "Domiciliario no encontrado con id: " + idDomiciliario));
    }

    @Override
    public List<Domiciliario> Domiciliarios() {
        return domiciliarioRepository.findAll();
    }

    @Override
    public void actualizarDomiciliario(String cedula, Domiciliario domiciliarioParcial) {
        Domiciliario domiciliario = domiciliarioRepository.findDomiciliarioByCedula(cedula)
                .orElseThrow(() -> new RuntimeException("Domiciliario no encontrado con cédula: " + cedula));

        if (domiciliarioParcial.getNombreDomiciliario() != null && !domiciliarioParcial.getNombreDomiciliario().isEmpty()) {
            domiciliario.setNombreDomiciliario(domiciliarioParcial.getNombreDomiciliario());
        }

        if (domiciliarioParcial.getCedula() != null && !domiciliarioParcial.getCedula().isEmpty()) {
            domiciliario.setCedula(domiciliarioParcial.getCedula());
        }

        if (domiciliarioParcial.getTelefono() != null && !domiciliarioParcial.getTelefono().isEmpty()) {
            domiciliario.setTelefono(domiciliarioParcial.getTelefono());
        }

        if (domiciliarioParcial.getTotalDomicilios() != null) {
            domiciliario.setTotalDomicilios(domiciliarioParcial.getTotalDomicilios());
        }

        domiciliarioRepository.save(domiciliario);
    }

    @Override
    public void eliminarDomiciliario(String cedula) {
        Domiciliario domiciliario = domiciliarioRepository.findDomiciliarioByCedula(cedula).orElseThrow(() ->
                new RuntimeException("Domiciliario no encontrado con cédula: " + cedula));
        Rapidin rapidin = rapidinRepository.findRapidinByDomiciliario(domiciliario);
        rapidinRepository.delete(rapidin);
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
