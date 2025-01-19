package com.rapidin.rapidinApp.service;

import com.rapidin.rapidinApp.dto.RapidinDTO;
import com.rapidin.rapidinApp.model.Domiciliario;
import com.rapidin.rapidinApp.model.Rapidin;
import com.rapidin.rapidinApp.repository.IDomiciliarioRepository;
import com.rapidin.rapidinApp.repository.IRapidinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RapidinService implements IRapidinService {

    @Autowired
    private IRapidinRepository rapidinRepository;

    @Autowired
    private IDomiciliarioRepository domiciliarioRepository;

    @Override
    public List<Rapidin> obtenerRapidinPorDiaEspecifico(LocalDate fecha) {
        return rapidinRepository.findRapidinByFecha(fecha);
    }

    @Override
    public List<Rapidin> obtenerRapidines() {
        return rapidinRepository.findAll();
    }

    @Override
    public List<Rapidin> obtenerRapidinesPorDiaActual() {
        LocalDate hoy = LocalDate.now();
        return rapidinRepository.findRapidinByFecha(hoy);
    }


    @Override
    public void resetearValores() {

        LocalDate nuevaFecha = LocalDate.now();

        if (!rapidinRepository.findRapidinByFecha(nuevaFecha).isEmpty()) {
            throw new IllegalStateException("Los valores ya han sido reiniciados para la fecha: " + nuevaFecha);
        }

        List<Domiciliario> domiciliarios = domiciliarioRepository.findAll();
        if (domiciliarios.isEmpty()) {
            throw new RuntimeException("No se encontraron domiciliarios para crear Rapidin.");
        }

        List<Rapidin> nuevosRegistros = new ArrayList<>();

        for (Domiciliario domiciliario : domiciliarios) {
            Rapidin nuevoRapidin = new Rapidin();
            nuevoRapidin.setFecha(nuevaFecha);
            nuevoRapidin.setDomiciliario(domiciliario);
            nuevoRapidin.setTotalIngreso(0.0);
            nuevosRegistros.add(nuevoRapidin);
        }

        rapidinRepository.saveAll(nuevosRegistros);
    }


    @Override
    public RapidinDTO convertirRapidinDTO(Rapidin rapidin) {
        RapidinDTO rapidinDTO = new RapidinDTO();
        rapidinDTO.setId(rapidin.getId());
        rapidinDTO.setFecha(rapidin.getFecha());
        rapidinDTO.setNombreDomiciliario(rapidin.getDomiciliario().getNombreDomiciliario());
        rapidinDTO.setTotalDomiciliario(rapidin.getDomiciliario().getTotalDomicilios());
        rapidinDTO.setTotalIngreso(rapidin.getTotalIngreso());

        return rapidinDTO;
    }
}
