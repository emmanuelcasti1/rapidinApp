package com.rapidin.rapidinApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DomicilioDTO {
    private Long id;
    private String nombreNegocio;
    private String nombreDomiciliario;
    private Double precioDomicilio;
    private LocalDate fechaDomicilio;
}
