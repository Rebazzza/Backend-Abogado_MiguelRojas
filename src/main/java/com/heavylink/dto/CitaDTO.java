package com.heavylink.dto;

import com.heavylink.model.Abogado;
import com.heavylink.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDTO {
    private Integer idCita;
    private String asuntoLegal;
    private String detallesAdicionales;
    private LocalDateTime fechaHora;
    private boolean activa;
    private Cliente cliente;
    private Abogado abogado;
}
