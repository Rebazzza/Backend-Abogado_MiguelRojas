package com.heavylink.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Integer idUsuario;
    private String username;
    private Integer idRol;
    private String rolName;
    private String password;
    private Integer idAbogado;
    private String abogadoNombre;
}
