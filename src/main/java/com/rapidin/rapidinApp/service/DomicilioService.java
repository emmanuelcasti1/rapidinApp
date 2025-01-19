package com.rapidin.rapidinApp.service;

import com.rapidin.rapidinApp.dto.DomicilioDTO;
import com.rapidin.rapidinApp.model.Domiciliario;
import com.rapidin.rapidinApp.model.Domicilio;
import com.rapidin.rapidinApp.model.Rapidin;
import com.rapidin.rapidinApp.repository.IDomiciliarioRepository;
import com.rapidin.rapidinApp.repository.IDomicilioRepository;
import com.rapidin.rapidinApp.repository.IRapidinRepository;
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

    @Autowired
    private IRapidinRepository rapidinRepository;

    @Override
    public void crearDomicilio(DomicilioDTO domicilioDTO) {
        Domiciliario domiciliario = domiciliarioRepository.findById(domicilioDTO.getDomiciliarioId())
                .orElseThrow(() -> new RuntimeException("No existe el domiciliario con el id: " + domicilioDTO.getDomiciliarioId()));

        Domicilio domicilio = new Domicilio();
        domicilio.setNombreNegocio(domicilioDTO.getNombreNegocio());
        domicilio.setPrecioDomicilio(domicilioDTO.getPrecioDomicilio());
        domicilio.setFechaDomicilio(LocalDate.now());
        domicilio.setDomiciliario(domiciliario);

        double nuevoTotalDomicilios = domiciliario.getTotalDomicilios() + domicilio.getPrecioDomicilio();
        domiciliario.setTotalDomicilios(nuevoTotalDomicilios);

        domiciliarioRepository.save(domiciliario);

        domicilioRepository.save(domicilio);

        actualizarRapidin(domicilio.getFechaDomicilio(), domicilio);
    }

    @Override
    public Domicilio buscarDomicilioPorId(Long id) {
        return domicilioRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Domicilio no encontrado con id: " + id));
    }

    @Override
    public void actualizarDomicilio(Long idDomicilio, DomicilioDTO domicilioParcial) {
        Domicilio domicilioExistente = domicilioRepository.findById(idDomicilio).orElseThrow(() ->
                new RuntimeException("Domicilio no encontrado con id: " + idDomicilio));

        if (domicilioParcial.getNombreNegocio() != null) {
            domicilioExistente.setNombreNegocio(domicilioParcial.getNombreNegocio());
        }

        if (domicilioParcial.getPrecioDomicilio() != null) {
            domicilioExistente.setPrecioDomicilio(domicilioParcial.getPrecioDomicilio());
        }

        if (domicilioParcial.getFechaDomicilio() != null) {
            domicilioExistente.setFechaDomicilio(domicilioParcial.getFechaDomicilio());
        }

        if (domicilioParcial.getDomiciliarioId() != null) {
            Domiciliario domiciliarioExistente = domiciliarioRepository.findById(domicilioParcial.getDomiciliarioId())
                    .orElseThrow(() ->
                    new RuntimeException("Domiciliario no encontrado con id: " + domicilioParcial.getId()));
            domicilioExistente.setDomiciliario(domiciliarioExistente);
        }

        // Guardar los cambios
        domicilioRepository.save(domicilioExistente);

    }

    @Override
    public List<Domicilio> domiciliosPorFecha(LocalDate fecha) {
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

    @Override
    public void actualizarRapidin(LocalDate fecha, Domicilio domicilio) {
        Rapidin rapidin = rapidinRepository.findRapidinByFechaAndDomiciliario(fecha, domicilio.getDomiciliario());
        if (rapidin != null) {
            double nuevoTotalDomiciliario = domicilio.getDomiciliario().getTotalDomicilios();
            double nuevoTotalIngreso = nuevoTotalDomiciliario * 0.20;

            rapidin.setTotalIngreso(nuevoTotalIngreso);
            rapidinRepository.save(rapidin);
        } else {
            System.out.println("No se encontró un Rapidin para la fecha y domiciliario especificados.");
        }

    }

    @Override
    public List<Domicilio> domicilios() {
        return domicilioRepository.findAll();
    }

}
