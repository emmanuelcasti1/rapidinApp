package com.rapidin.rapidinApp.service;

import com.rapidin.rapidinApp.model.Domiciliario;
import com.rapidin.rapidinApp.model.Domicilio;
import com.rapidin.rapidinApp.repository.IDomiciliarioRepository;
import com.rapidin.rapidinApp.repository.IDomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DomicilioService implements IDomicilioService {

    @Autowired
    private IDomicilioRepository domicilioRepository;

    @Autowired
    private IDomiciliarioRepository domiciliarioRepository;

    @Override
    public void crearDomicilio(Domicilio domicilio) {
        domicilioRepository.save(domicilio);
    }

    @Override
    public Domicilio buscarDomicilioPorId(Long id) {
        return domicilioRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Domicilio no encontrado con id: " + id));
    }

    @Override
    public void actualizarDomicilio(Long idDomicilio, String nombreNegocioNuevo, Double precioDomicilioNuevo,
                                    LocalDate fechaDomicilioNueva, Domiciliario domiciliarioNuevo) {
        Domicilio domicilio = domicilioRepository.findById(idDomicilio).orElseThrow(() ->
                new RuntimeException("Domicilio no encontrado con id: " + idDomicilio));

        if(nombreNegocioNuevo != null && !nombreNegocioNuevo.isEmpty()){
            domicilio.setNombreNegocio(nombreNegocioNuevo);
        }
        if (precioDomicilioNuevo != null && precioDomicilioNuevo > 0) {
            domicilio.setPrecioDomicilio(precioDomicilioNuevo);
        }

        if (fechaDomicilioNueva != null) {
            domicilio.setFechaDomicilio(fechaDomicilioNueva);
        }

        if (domiciliarioNuevo != null) {
            Domiciliario domiciliarioExistente = domiciliarioRepository.findById(domiciliarioNuevo.getId()).orElseThrow(() ->
                    new RuntimeException("Domiciliario no encontrado con id: " + domiciliarioNuevo.getId()));
            domicilio.setDomiciliario(domiciliarioExistente);
        }

        // Guardar los cambios
        domicilioRepository.save(domicilio);

    }

    @Override
    public List<Domicilio> Domicilios(LocalDate fecha) {
        List<Domicilio> domicilios = domicilioRepository.findAll();
        List<Domicilio> domiciliosFecha = new ArrayList<>();

        for(Domicilio domicilio : domicilios){
            if(domicilio.getFechaDomicilio().equals(fecha)){
                domiciliosFecha.add(domicilio);
            }
        }
        return domiciliosFecha;
    }

    @Override
    public void eliminarDomicilio(Long idDomicilio) {
        domicilioRepository.deleteById(idDomicilio);
    }
}
