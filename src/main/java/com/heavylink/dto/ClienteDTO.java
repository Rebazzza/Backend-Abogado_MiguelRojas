package com.heavylink.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Integer idCliente;
    private String nombre;
    private String descripcion;
    private String dni;
    private String RUC;
    private String telefono;
    private String dirección;
    private String correo;
    private boolean estado;
    private String tipoCliente;
}
