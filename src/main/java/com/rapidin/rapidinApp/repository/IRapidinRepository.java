package com.rapidin.rapidinApp.repository;

import com.rapidin.rapidinApp.model.Domiciliario;
import com.rapidin.rapidinApp.model.Rapidin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IRapidinRepository extends JpaRepository<Rapidin, Long> {
    List<Rapidin> findRapidinByFecha(LocalDate fecha);
    Rapidin findRapidinByFechaAndDomiciliario(LocalDate fecha, Domiciliario domiciliario);
    List<Rapidin> findRapidinByDomiciliario(Domiciliario domiciliario);
}
