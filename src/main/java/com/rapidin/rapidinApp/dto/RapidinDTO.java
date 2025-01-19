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
public class RapidinDTO {
    private Long id;
    private LocalDate fecha;
    private String nombreDomiciliario;
    private Double totalDomiciliario;
    private Double totalIngreso;
}
