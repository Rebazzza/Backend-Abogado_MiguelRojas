package com.heavylink.dto;

import com.heavylink.model.Expediente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentosLegalesDTO {
    private Integer idDocumento;
    private String nombreArchivo;
    private String ruta;
    private LocalDateTime fechaCreacion;
    private Integer idExpediente;

}
