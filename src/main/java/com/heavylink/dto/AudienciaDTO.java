package com.heavylink.dto;


import com.heavylink.model.Abogado;
import com.heavylink.model.Caso;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudienciaDTO {

    private Integer idAudiencia;
    private LocalDate fecha;
    private String direccion;
    private Abogado abogado;
    private LocalDateTime hora;
    private String  tipoAudiencia;
    private String lugarLink;
    private Caso caso;

}
