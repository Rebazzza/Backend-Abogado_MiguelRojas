package com.heavylink.dto;

import com.heavylink.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class NotificacionDTO {

    private Integer idNotificacion;
    private String titulo;
    private String mensaje;
    private boolean leido;
    private LocalDateTime fecha;
    private Integer idUsuario;

}
