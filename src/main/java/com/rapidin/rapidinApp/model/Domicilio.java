package com.rapidin.rapidinApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "domicilios")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreNegocio;
    private Double precioDomicilio;
    private LocalDate fechaDomicilio;
    @ManyToOne
    @JoinColumn(name = "domiciliario_id", nullable = false)
    private Domiciliario domiciliario;
}
