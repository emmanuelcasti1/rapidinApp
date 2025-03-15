package com.rapidin.rapidinApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Domiciliario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombreDomiciliario;

    @NotNull
    private String telefono;

    @NotNull
    @Column(unique = true)
    private String cedula;
    private Double totalDomicilios = 0.0;
    @OneToMany(mappedBy = "domiciliario", cascade = CascadeType.ALL, orphanRemoval = true)
   @JsonIgnoreProperties("domiciliario")
    private List<Domicilio> domicilios = new ArrayList<>();
}
