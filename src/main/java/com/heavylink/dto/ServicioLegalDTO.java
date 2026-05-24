package com.heavylink.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicioLegalDTO {

    private Integer idServicio;


    private String nombre;


    private String descripcion;


    private String estado;

    private Float costoBase;
}
