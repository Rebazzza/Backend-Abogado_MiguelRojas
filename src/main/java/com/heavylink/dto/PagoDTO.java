package com.heavylink.dto;

import com.heavylink.model.Caso;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class PagoDTO {

    private Integer idPago;
    private String metodoPago;
    private boolean estadoPago;
    private float monto;
    private LocalDateTime fechaPago;
    private Integer idCaso;
}
