package com.heavylink.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbogadoDTO {
    private Integer idAbogado;
    private String nombre;
    private String apellido;
    private String telefono;
    private String dni;
    private String correo;
    private String especialidad;
    private boolean estado;
    private Integer idUsuario;
}
