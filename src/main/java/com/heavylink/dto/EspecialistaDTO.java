package com.heavylink.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EspecialistaDTO {
    private Integer idEspecialista;
    private String nombre;
    private String descripcion;
    private String estado;
    private String dni;
    private boolean disponibilidad;
    private String telefono;
    private String correo;


}
