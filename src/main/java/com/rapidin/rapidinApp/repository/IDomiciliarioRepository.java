package com.rapidin.rapidinApp.repository;

import com.rapidin.rapidinApp.model.Domiciliario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDomiciliarioRepository extends JpaRepository<Domiciliario, Long> {
    Optional <Domiciliario> findDomiciliarioByCedula(String cedula);
}
