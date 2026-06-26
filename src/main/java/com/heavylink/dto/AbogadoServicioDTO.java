package com.heavylink.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.heavylink.model.Abogado;
import com.heavylink.model.ServicioLegal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbogadoServicioDTO {
    private Integer idAbogadoServicio;
    private Integer idAbogado;
    private Integer idServicioLegal;
}
