package com.heavylink.dto;

import com.heavylink.model.Abogado;
import com.heavylink.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CasoDTO {
    private Integer idCaso;
    private String titulo;
    private String descripcion;
    private boolean estado;
    private Abogado abogado;
    private Cliente cliente;
}
