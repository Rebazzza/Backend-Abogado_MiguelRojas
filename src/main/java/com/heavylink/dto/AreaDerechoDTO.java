package com.heavylink.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaDerechoDTO {
    private Integer idArea;
    private String nombre;
    private String descripcion;
    private boolean estado;

}
