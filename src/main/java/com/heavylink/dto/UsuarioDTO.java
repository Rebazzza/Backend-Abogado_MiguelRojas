package com.heavylink.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioDTO {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idUsuario;
	@Column(nullable = false, length = 50)//name="nombre")
    private String nombre;
	@Column(nullable = false, length = 50)//name="nombre")
    private String rol;
	@Column(nullable = false, length = 50)//name="nombre")
    private String contraseña;
	
	
	
	

}
