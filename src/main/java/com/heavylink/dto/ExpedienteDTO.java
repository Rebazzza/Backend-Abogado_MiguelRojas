package com.heavylink.dto;

import com.heavylink.model.Caso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor


public class ExpedienteDTO {

    private Integer idExPediente;
    private String titulo;
    private String tipoExpediente;
    private String resumenExpediente;
    private boolean estadoExpediente;
    private String fechaInicio;
    private String fechaCierre;
    private Caso caso;
}
